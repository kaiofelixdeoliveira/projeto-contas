package com.br.app.contas.data.repositories;

import com.br.app.contas.data.client.BacenClient;
import com.br.app.contas.data.client.CadastroClient;
import com.br.app.contas.data.client.dto.BacenRequest;
import com.br.app.contas.data.client.dto.CadastroResponse;
import com.br.app.contas.data.datasources.ContaJpaRepository;
import com.br.app.contas.data.datasources.TransferenciaJpaRepository;
import com.br.app.contas.data.entities.Conta;
import com.br.app.contas.data.enums.StatusConta;
import com.br.app.contas.data.enums.StatusTransferencia;
import com.br.app.contas.domain.model.ContaModel;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import com.br.app.core.exeptions.*;
import com.br.app.core.utils.RetryUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private ObjectMapper mapper;


    @Override
    public void realizarTransferencia(TransferenciaModel transferencia) {


        String contaOrigem = transferencia.getContaOrigem().getNumeroConta();
        String contaDestino = transferencia.getContaDestino().getNumeroConta();

        // Buscar nome do cliente
        var cadastroResponse = obterNomeCliente(transferencia.getCpf());


        // Validar se a conta origem está ativa
        var contaOrigemAtiva = validarContaAtiva(contaOrigem);
        if (!contaOrigemAtiva) {
            throw new ValidaContaOrigemException("Conta origem não está ativa");
        }

        // Validar se a conta destino está ativa
        var contaDestinoAtiva = validarContaAtiva(contaDestino);
        if (!contaDestinoAtiva) {
            throw new ValidaContaDestinoException("Conta destino não está ativa");
        }

        // Validar se o saldo é suficiente na conta de origem
        var contaOrigemSaldo = consultaSaldo(contaOrigem);
        if (contaOrigemSaldo.getSaldo() < transferencia.getValor()) {
            throw new ValidaSaldoException("Saldo insuficiente");
        }

        // Validar limite disponível
        validarLimiteDisponivel(contaOrigem, transferencia.getValor());

        // Validar limite diário
        if (transferencia.getValor() > 1000.0) {
            throw new LimiteDiarioExcedidoException();
        }

        //consultar saldo da conta de destino
        var contaDestinoSaldo = consultaSaldo(contaDestino);

        // Atualizar saldo das contas
        var novoLimiteContaOrigem = atualizarLimite(contaOrigem, contaOrigemSaldo.getSaldo() - transferencia.getValor());
        var novoLimiteContaContaDestino = atualizarLimite(contaDestino, contaDestinoSaldo.getSaldo() + transferencia.getValor());

        transferencia.setContaOrigem(novoLimiteContaOrigem);
        transferencia.setContaDestino(novoLimiteContaContaDestino);
        transferencia.setStatus(StatusTransferencia.CONCLUIDA.name());
        transferencia.setDataHora(LocalDateTime.now());

        //Atualizar saldo da transferência
        updateTransferencia(transferencia);

        BacenRequest bacenRequest = BacenRequest.transferenciaToBacenRequest(transferencia);
        String nome = findNome(cadastroResponse);
        bacenRequest.setNome(nome);
        // Notificar BACEN
        notificarTransacao(bacenRequest);

    }

    void updateTransferencia(TransferenciaModel transferenciaModel) {
        var transferencia = TransferenciaModel.transferenciaModelToTransferencia(transferenciaModel);
        transferenciaJpaRepository.save(transferencia);

    }

    String findNome(List<CadastroResponse> cadastroResponse) {
        return cadastroResponse.stream()
                .findFirst()
                .map(CadastroResponse::getNome)
                .orElseThrow(() -> new CadastroNaoEncontradoException("Cadastros não encontrada"));
    }


    @Override
    public void notificarTransacao(BacenRequest bacenRequest) {

        try {
            RetryUtil.executeWithRetry(() -> bacenClient.notificarTransacao(bacenRequest), 3, 1000); // 3 tentativas com delay inicial de 1s
        } catch (Exception e) {
            // Logar a falha e tratar a exceção (ex.: notificar o cliente)
            throw new RateLimitException("Falha ao notificar BACEN");
        }
    }


    @Override
    public ContaModel atualizarLimite(String numeroConta, Double novoLimite) throws ContaNaoEncontradaException {
        Conta conta = contaJpaRepository.findByNumeroConta(numeroConta);
        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada");
        }

        conta.setSaldo(novoLimite);
        contaJpaRepository.save(conta);
        var contaModel = ContaModel.contaToContaModel(conta);
        return contaModel;
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
        Conta conta = contaJpaRepository.findByNumeroConta(numeroConta);
        return ContaModel.contaToContaModel(conta);
    }


    @Override
    public boolean validarContaAtiva(String conta) {

        // Simular consulta externa (substituir por lógica real)
        Conta contaAtiva = contaJpaRepository.findByNumeroContaAndStatus(conta, StatusConta.ATIVA.name());
        if (contaAtiva != null && contaAtiva.getStatus().equals(StatusConta.ATIVA.name())) {
            return true;
        }
        throw new ContaNaoEncontradaException("Conta não encontrada");

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

    @Override
    @CircuitBreaker(name = "clienteServiceCircuitBreaker", fallbackMethod = "fallbackBuscarNomeCliente")
    public List<CadastroResponse> obterNomeCliente(String cpf) {
        try {
            return cadastroClient.obterNomeCliente(cpf);
        } catch (FeignException e) {
            throw new CadastroException(e.status(), e.getMessage());
        }
    }

    private String fallbackBuscarNomeCliente(String cpf, Throwable t) {
        // Log error and provide a default value or alternative logic
        System.err.println("Falha ao buscar nome do cliente: " + t.getMessage());
        return "Cliente indisponível";
    }
}
