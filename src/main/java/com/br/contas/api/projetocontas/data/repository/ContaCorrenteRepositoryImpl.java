package com.br.contas.api.projetocontas.data.repository;

import com.br.contas.api.projetocontas.data.datasources.ContaCorrenteJpaRepository;
import com.br.contas.api.projetocontas.domain.entities.ContaCorrente;
import com.br.contas.api.projetocontas.domain.entities.Transferencia;
import com.br.contas.api.projetocontas.domain.repositories.ContaCorrenteRepository;

public class ContaCorrenteRepositoryImpl implements ContaCorrenteRepository {
     final ContaCorrenteJpaRepository contaCorrenteJpaRepository;

    public ContaCorrenteRepositoryImpl(ContaCorrenteJpaRepository contaCorrenteJpaRepository) {
        this.contaCorrenteJpaRepository = contaCorrenteJpaRepository;
    }


    @Override
    public ContaCorrente consultaSaldo(String numeroConta) {
        ContaCorrente contaCorrente = contaCorrenteJpaRepository.findByNumero(numeroConta);
        return contaCorrente;
    }


    @Override
    public void validarContaCorrenteAtiva(ContaCorrente contaCorrente) {

    }

    @Override
    public void validarLimiteDisponivel(ContaCorrente contaCorrente, Transferencia valor) {

    }
}
