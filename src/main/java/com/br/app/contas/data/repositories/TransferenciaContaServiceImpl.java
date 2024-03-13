package com.br.app.contas.data.repositories;

import com.br.app.contas.data.client.BacenClient;
import com.br.app.contas.data.client.CadastroClient;
import com.br.app.contas.data.datasources.ContaJpaRepository;
import com.br.app.contas.data.datasources.TransferenciaJpaRepository;
import com.br.app.contas.domain.model.ContaModel;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import com.br.app.core.exeptions.LimiteDiarioExcedidoException;
import com.br.app.core.exeptions.RateLimitException;
import com.br.app.core.utils.RetryUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class TransferenciaContaServiceImpl implements TransferenciaContaRepository {

    private Map<String, Boolean> contasAtivasCache; // Simulação de cache para agilizar validação
    private CadastroClient cadastroClient;
    private BacenClient bacenClient;
    private TransferenciaJpaRepository transferenciaJpaRepository;
    private ContaJpaRepository contaJpaRepository;

    @Override
    public String obterNomeCliente(String cpf) {

        return cadastroClient.obterNomeCliente(cpf);
    }

    @Override
    public void realizarTransferencia(TransferenciaModel transferencia) {

        // Buscar nome do cliente
        String nomeCliente = cadastroClient.obterNomeCliente(transferencia.getCpf());

        // Validar se a conta origem está ativa
        var contaOrigemAtiva = validarContaAtiva(transferencia.getContaOrigem());
        if (!contaOrigemAtiva) {
            throw new RuntimeException("Conta origem não está ativa");
        }

        // Validar se a conta destino está ativa
        var contaDestinoAtiva = validarContaAtiva(transferencia.getContaDestino());
        if (!contaDestinoAtiva) {
            throw new RuntimeException("Conta destino não está ativa");
        }

        // Validar se o saldo é suficiente na conta de origem
        var contaOrigemSaldo = consultaSaldo(transferencia.getContaOrigem());
        if (contaOrigemSaldo.getSaldo() < transferencia.getValor()) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // Validar limite disponível
        validarLimiteDisponivel(transferencia.getContaOrigem(), transferencia.getValor());

        // Validar limite diário
        if (transferencia.getValor() > 1000.0) {
            throw new LimiteDiarioExcedidoException();
        }

        //consultar saldo da conta de destino
        var contaDestinoSaldo = consultaSaldo(transferencia.getContaDestino());

        // Atualizar saldo das contas
        atualizarLimite(transferencia.getContaOrigem(), contaOrigemSaldo.getSaldo() - transferencia.getValor());
        atualizarLimite(transferencia.getContaDestino(), contaDestinoSaldo.getSaldo() + transferencia.getValor());


        //Atualizar saldo da transferência
        transferenciaJpaRepository.updateTransferencia(transferencia.getContaDestino(), transferencia.getValor());

        // Notificar BACEN
        notificarTransacao(transferencia);

    }


    @Override
    public void notificarTransacao(TransferenciaModel transferencia) {
        try {
            RetryUtil.executeWithRetry(() -> bacenClient.notificarTransacao(transferencia), 3, 1000); // 3 tentativas com delay inicial de 1s
        } catch (Exception e) {
            // Logar a falha e tratar a exceção (ex.: notificar o cliente)
            throw new RateLimitException("Falha ao notificar BACEN");
        }
    }


    @Override
    public void atualizarLimite(String numeroConta, Double saldo) {
        atualizarLimite(numeroConta, saldo);
    }


    @Override
    public Double getValorTotalTransferidoPorDia(String numeroConta, LocalDate data) {
        // Obter todas as transferências da conta na data informada
        List<TransferenciaModel> transferencias = transferenciaJpaRepository.findByContaIdAndDataHoraBetween(numeroConta, data.atStartOfDay(), data.atStartOfDay());

        // Somar os valores das transferências
        Double totalTransferido = 0.0;
        for (TransferenciaModel transferencia : transferencias) {
            totalTransferido += transferencia.getValor();
        }

        return totalTransferido;
    }


    @Override
    public ContaModel consultaSaldo(String numeroConta) {
        ContaModel conta = contaJpaRepository.findByNumeroConta(numeroConta);
        return conta;
    }


    @Override
    public boolean validarContaAtiva(String conta) {

        // Verificar se a conta já está na cache
        if (contasAtivasCache.containsKey(conta)) {
            return contasAtivasCache.get(conta);
        }

        // Simular consulta externa (substituir por lógica real)
        boolean isActive = contaJpaRepository.findIsActiveByNumeroConta(conta);
        contasAtivasCache.put(conta, isActive);

        return isActive;
    }

    @Override
    public boolean validarLimiteDisponivel(String conta, Double valorTransferencia) {


        // Obter saldo disponível da conta
        var saldoDisponivel = consultaSaldo(conta);

        // Validar se o valor da transferência excede o saldo disponível
        if (valorTransferencia > saldoDisponivel.getSaldo()) {
            return false;
        }

        // Obter limite diário de transferência
        Double limiteDiario = 1000.0;

        // Obter total de transferências já realizadas no dia
        Double totalTransferidoHoje = getValorTotalTransferidoPorDia(conta, LocalDate.now());

        // Validar se o valor da transferência excede o limite diário
        if (valorTransferencia + totalTransferidoHoje > limiteDiario) {
            return false;
        }

        return true;
    }
}
