package com.br.app.contas.data.repositories;

import com.br.app.contas.data.datasources.ContaJpaRepository;
import com.br.app.contas.data.datasources.TransferenciaJpaRepository;
import com.br.app.contas.data.entities.Conta;
import com.br.app.contas.data.enums.StatusConta;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import com.br.app.core.exeptions.ValidaSaldoException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferenciaContaServiceImplTest {

    @Mock
    private ContaJpaRepository contaJpaRepository;

    @Mock
    private TransferenciaJpaRepository transferenciaJpaRepository;

    @InjectMocks
    TransferenciaContaServiceImpl transferenciaContaRepository;

    //@Test
    public void testRealizarTransferencia_SaldoInsuficiente() {
        // Arrange
        TransferenciaModel transferencia = new TransferenciaModel();
        transferencia.setValor(1001.0);
        String contaOrigem = "123456";
        String contaDestino = "654321";

        // Mock da ContaJpaRepository
        Mockito.when(contaJpaRepository.findByNumeroConta(contaOrigem))
                .thenReturn(new Conta(contaOrigem, 1000.0, StatusConta.ATIVA));
        // Act
        try {
            transferenciaContaRepository.realizarTransferencia(transferencia);
            fail("Exceção ValidaSaldoException não foi lançada");
        } catch (ValidaSaldoException e) {
            // Assert
            assertEquals("Saldo insuficiente", e.getMessage());
        }
    }
}