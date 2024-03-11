package com.br.app.contas.data.repositories;


import com.br.app.contas.data.datasources.ContaCorrenteJpaRepository;
import com.br.app.contas.domain.model.ContaCorrenteModel;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.ContaCorrenteRepository;
import com.br.app.contas.domain.repositories.TransferenciaRepository;
import com.br.app.core.exeptions.LimiteDiarioExcedidoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class ContaCorrenteRepositoryImpl implements ContaCorrenteRepository {
    final ContaCorrenteJpaRepository contaCorrenteJpaRepository;
    private Map<String, Boolean> contasAtivasCache; // Simulação de cache para agilizar validação

    final TransferenciaRepository transferenciaRepository;

    public ContaCorrenteRepositoryImpl(TransferenciaRepository transferenciaRepository,ContaCorrenteJpaRepository contaCorrenteJpaRepository) {
        this.contaCorrenteJpaRepository = contaCorrenteJpaRepository;
        this.transferenciaRepository = transferenciaRepository;
    }


    @Override
    public ContaCorrenteModel consultaSaldo(String numeroConta) {
        ContaCorrenteModel contaCorrente = contaCorrenteJpaRepository.findByNumeroConta(numeroConta);
        return contaCorrente;
    }


    @Override
    public boolean validarContaCorrenteAtiva(String contaCorrente) {

        // Verificar se a conta já está na cache
        if (contasAtivasCache.containsKey(contaCorrente)) {
            return contasAtivasCache.get(contaCorrente);
        }

        // Simular consulta externa (substituir por lógica real)
        boolean isActive = contaCorrenteJpaRepository.findIsActiveByNumeroConta(contaCorrente);
        contasAtivasCache.put(contaCorrente, isActive);

        return isActive;
    }

    @Override
    public boolean validarLimiteDisponivel(String contaCorrente, Double valorTransferencia) {


        // Obter saldo disponível da conta
        var saldoDisponivel=consultaSaldo(contaCorrente);

        // Validar se o valor da transferência excede o saldo disponível
        if (valorTransferencia > saldoDisponivel.getSaldo()) {
            return false;
        }

        // Obter limite diário de transferência
        Double limiteDiario = 1000.0;

        // Obter total de transferências já realizadas no dia
        Double totalTransferidoHoje = transferenciaRepository.getValorTotalTransferidoPorDia(contaCorrente, LocalDate.now());

        // Validar se o valor da transferência excede o limite diário
        if (valorTransferencia + totalTransferidoHoje > limiteDiario) {
            return false;
        }

        return true;
    }

    @Override
    public void atualizarLimite(String contaOrigem, Double valor) {

    }
}