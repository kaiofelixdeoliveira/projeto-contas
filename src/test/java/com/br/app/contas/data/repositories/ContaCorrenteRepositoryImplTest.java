package com.br.app.contas.data.repositories;

import com.br.app.contas.data.datasources.ContaJpaRepository;
import com.br.app.contas.domain.model.ContaModel;
import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ContaRepositoryImplTest {

    @Mock
    private ContaJpaRepository contaJpaRepository;

    @InjectMocks
    private TransferenciaContaRepository transferenciaRepository;
    ;

    private Map<String, Boolean> contasAtivasCache;

    @BeforeEach
    void setUp() {
    }

    @Test
    void consultaSaldo_contaExistenteComSaldoPositivo() {
        // Arrange
        String numeroConta = "123456";
        Double saldoEsperado = 1000.0;
        ContaModel contaMock = mock(ContaModel.class);
        when(contaMock.getNumeroConta()).thenReturn(numeroConta);
        when(contaMock.getSaldo()).thenReturn(saldoEsperado);
        when(contaJpaRepository.findByNumeroConta(numeroConta)).thenReturn(contaMock);

        // Act
        ContaModel conta = transferenciaRepository.consultaSaldo(numeroConta);

        // Assert
        assertNotNull(conta);
        assertEquals(numeroConta, conta.getNumeroConta());
        assertEquals(saldoEsperado, conta.getSaldo());
    }

    @Test
    void consultaSaldo_contaInexistente() {
        // Arrange
        String numeroConta = "987654";
        when(contaJpaRepository.findByNumeroConta(numeroConta)).thenReturn(null);

        // Act
        ContaModel conta = transferenciaRepository.consultaSaldo(numeroConta);

        // Assert
        assertNull(conta);
    }

    @Test
    void consultaSaldo_erroNaConsulta() {
        // Arrange
        String numeroConta = "123456";
        when(contaJpaRepository.findByNumeroConta(numeroConta)).thenThrow(new RuntimeException());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> transferenciaRepository.consultaSaldo(numeroConta));
    }

    @Test
    void validarContaAtiva_contaNaCacheComStatusAtivo() {
        // Arrange
        String numeroConta = "123456";
        contasAtivasCache.put(numeroConta, true);

        // Act
        boolean isActive = transferenciaRepository.validarContaAtiva(numeroConta);

        // Assert
        assertTrue(isActive);
        verify(contaJpaRepository, never()).findIsActiveByNumeroConta(numeroConta);
    }

    @Test
    void validarContaAtiva_contaNaCacheComStatusInativo() {
        // Arrange
        String numeroConta = "123456";
        contasAtivasCache.put(numeroConta, false);

        // Act
        boolean isActive = transferenciaRepository.validarContaAtiva(numeroConta);

        // Assert
        assertFalse(isActive);
        verify(contaJpaRepository, never()).findIsActiveByNumeroConta(numeroConta);
    }

    @Test
    void validarContaAtiva_contaNaoNaCache() {
        // Arrange
        String numeroConta = "123456";
        boolean isActiveMock = true; // Simule o valor retornado do banco
        when(contaJpaRepository.findIsActiveByNumeroConta(numeroConta)).thenReturn(isActiveMock);

        // Act
        boolean isActive = transferenciaRepository.validarContaAtiva(numeroConta);

        // Assert
        assertTrue(isActive);
        verify(contaJpaRepository).findIsActiveByNumeroConta(numeroConta);
        contasAtivasCache.containsKey(numeroConta); // Verifica se a conta foi adicionada ao cache
    }

}