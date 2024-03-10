package com.br.contas.api.projetocontas.data.repository;

import com.br.contas.api.projetocontas.core.exception.LimiteDiarioExcedidoException;
import com.br.contas.api.projetocontas.core.exception.RateLimitException;
import com.br.contas.api.projetocontas.data.client.BacenClient;
import com.br.contas.api.projetocontas.data.client.CadastroClient;
import com.br.contas.api.projetocontas.data.client.ContaCorrenteClient;
import com.br.contas.api.projetocontas.domain.entities.Transferencia;
import com.br.contas.api.projetocontas.domain.repositories.TransferenciaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@CircuitBreaker(name = "transferencias")
public class TransferenciaRepositoryImpl implements TransferenciaRepository {

    private final CadastroClient cadastroClient;
    private final ContaCorrenteClient contaCorrenteClient;
    private final BacenClient bacenClient;

    public TransferenciaRepositoryImpl(CadastroClient cadastroClient, ContaCorrenteClient contaCorrenteClient, BacenClient bacenClient) {
        this.cadastroClient = cadastroClient;
        this.contaCorrenteClient = contaCorrenteClient;
        this.bacenClient = bacenClient;
    }

    @Override
    public void realizarTransferencia(Transferencia transferencia) {

        // Buscar nome do cliente
        String nomeCliente = cadastroClient.obterNomeCliente(transferencia.getCpf());

        // Validar conta corrente ativa
        contaCorrenteClient.validarContaCorrenteAtiva(transferencia.getContaCorrente());

        // Validar limite disponível
        contaCorrenteClient.validarLimiteDisponivel(transferencia.getContaCorrente(), transferencia.getValor());

        // Validar limite diário
        if (transferencia.getValor() > 1000.0) {
            throw new LimiteDiarioExcedidoException();
        }

        // Notificar BACEN
        try {
            notificarTransacao(transferencia);
        } catch (RateLimitException e) {
            // Implementar lógica de retry com backoff
            throw new RuntimeException("Falha ao notificar BACEN: " + e.getMessage());
        }

        // Registrar transacao

        // Notificar cliente

    }

    @Override
    public void notificarTransacao(Transferencia transferencia) {
        bacenClient.notificarTransacao(transferencia);
    }

    public void fallbackRealizarTransferencia(Transferencia transferencia, Throwable throwable) {
        // Registrar falha da transferência
        // Notificar cliente sobre falha

        throw new RuntimeException("Falha ao realizar transferência: " + throwable.getMessage());
    }

}