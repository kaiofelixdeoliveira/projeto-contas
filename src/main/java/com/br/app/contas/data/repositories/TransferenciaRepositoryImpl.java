package com.br.app.contas.data.repositories;


import com.br.app.contas.data.client.BacenClient;
import com.br.app.contas.data.client.CadastroClient;
import com.br.app.contas.data.datasources.TransferenciaJpaRepository;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.ContaCorrenteRepository;
import com.br.app.contas.domain.repositories.TransferenciaRepository;
import com.br.app.core.exeptions.LimiteDiarioExcedidoException;
import com.br.app.core.exeptions.RateLimitException;
import com.br.app.core.utils.RetryUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class TransferenciaRepositoryImpl implements TransferenciaRepository {

    final CadastroClient cadastroClient;
    final BacenClient bacenClient;
    final ContaCorrenteRepository contaCorrenteRepository;
    final TransferenciaJpaRepository transferenciaJpaRepository;

    public TransferenciaRepositoryImpl(TransferenciaJpaRepository transferenciaJpaRepository, CadastroClient cadastroClient, ContaCorrenteRepository contaCorrenteRepository, BacenClient bacenClient) {
        this.cadastroClient = cadastroClient;
        this.contaCorrenteRepository = contaCorrenteRepository;
        this.transferenciaJpaRepository = transferenciaJpaRepository;
        this.bacenClient = bacenClient;
    }

    @Override
    public void realizarTransferencia(TransferenciaModel transferencia) {

        // Buscar nome do cliente
        String nomeCliente = cadastroClient.obterNomeCliente(transferencia.getCpf());

        // Validar se a conta origem está ativa
        var contaCorrenteOrigemAtiva = contaCorrenteRepository.validarContaCorrenteAtiva(transferencia.getContaOrigem());
        if (!contaCorrenteOrigemAtiva) {
            throw new RuntimeException("Conta origem não está ativa");
        }

        // Validar se a conta destino está ativa
        var contaCorrenteDestinoAtiva = contaCorrenteRepository.validarContaCorrenteAtiva(transferencia.getContaDestino());
        if (!contaCorrenteDestinoAtiva) {
            throw new RuntimeException("Conta destino não está ativa");
        }

        // Validar se o saldo é suficiente na conta de origem
        var contaCorrenteOrigemSaldo = contaCorrenteRepository.consultaSaldo(transferencia.getContaOrigem());
        if (contaCorrenteOrigemSaldo.getSaldo() < transferencia.getValor()) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // Validar limite disponível
        contaCorrenteRepository.validarLimiteDisponivel(transferencia.getContaOrigem(), transferencia.getValor());

        // Validar limite diário
        if (transferencia.getValor() > 1000.0) {
            throw new LimiteDiarioExcedidoException();
        }

        //consultar saldo da conta de destino
        var contaCorrenteDestinoSaldo = contaCorrenteRepository.consultaSaldo(transferencia.getContaDestino());

        // Atualizar saldo das contas
        atualizarLimite(transferencia.getContaOrigem(), contaCorrenteOrigemSaldo.getSaldo() - transferencia.getValor());
        atualizarLimite(transferencia.getContaDestino(), contaCorrenteDestinoSaldo.getSaldo() + transferencia.getValor());


        //Atualizar saldo da transferência
        transferenciaJpaRepository.atualizarValor(transferencia);

        // Notificar BACEN
        notificarTransacao(transferencia);

    }

    @Override
    public void notificarTransacao(TransferenciaModel transferencia) {
        try {
            RetryUtil.executeWithRetry(() -> bacenClient.notificarTransacao(transferencia),3, 1000); // 3 tentativas com delay inicial de 1s
        } catch (Exception e) {
            // Logar a falha e tratar a exceção (ex.: notificar o cliente)
            throw new RateLimitException("Falha ao notificar BACEN");
        }
    }

    @Override
    public void atualizarLimite(String numeroConta, Double saldo) {
        contaCorrenteRepository.atualizarLimite(numeroConta, saldo);
    }

    @Override
    public Double getValorTotalTransferidoPorDia(String numeroConta, LocalDate data) {
        // Obter todas as transferências da conta na data informada
        List<TransferenciaModel> transferencias = transferenciaJpaRepository.findByContaCorrenteIdAndDataHoraBetween(numeroConta, data.atStartOfDay(), data.atStartOfDay());

        // Somar os valores das transferências
        Double totalTransferido = 0.0;
        for (TransferenciaModel transferencia : transferencias) {
            totalTransferido += transferencia.getValor();
        }

        return totalTransferido;
    }

    public void fallbackRealizarTransferencia(TransferenciaModel transferencia, Throwable throwable) {
        // Registrar falha da transferência
        // Notificar cliente sobre falha

        throw new RuntimeException("Falha ao realizar transferência: " + throwable.getMessage());
    }

}