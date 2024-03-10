package com.br.contas.api.projetocontas.domain.usecases;

import com.br.contas.api.projetocontas.core.exception.RateLimitException;
import com.br.contas.api.projetocontas.data.repository.CadastroClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.contas.api.projetocontas.data.repository.ContaCorrenteClient;
import com.br.contas.api.projetocontas.data.repository.BacenClient;
import com.br.contas.api.projetocontas.core.exception.LimiteDiarioExcedidoException;
import com.br.contas.api.projetocontas.domain.model.Transferencia;
@Service
@CircuitBreaker(name = "transferencias")
public class TransferenciaService {

    private final CadastroClient cadastroClient;
    private final ContaCorrenteClient contaCorrenteClient;
    private final BacenClient bacenClient;

    public TransferenciaService(CadastroClient cadastroClient, ContaCorrenteClient contaCorrenteClient, BacenClient bacenClient) {
        this.cadastroClient = cadastroClient;
        this.contaCorrenteClient = contaCorrenteClient;
        this.bacenClient = bacenClient;
    }

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
            bacenClient.notificarTransacao(transferencia);
        } catch (RateLimitException e) {
            // Implementar lógica de retry com backoff
            throw new RuntimeException("Falha ao notificar BACEN: " + e.getMessage());
        }

        // Registrar transacao

        // Notificar cliente

    }

    public void fallbackRealizarTransferencia(Transferencia transferencia, Throwable throwable) {
        // Registrar falha da transferência
        // Notificar cliente sobre falha

        throw new RuntimeException("Falha ao realizar transferência: " + throwable.getMessage());
    }

}