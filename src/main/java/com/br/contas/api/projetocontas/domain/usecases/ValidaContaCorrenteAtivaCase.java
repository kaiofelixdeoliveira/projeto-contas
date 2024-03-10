package com.br.contas.api.projetocontas.domain.usecases;

import com.br.contas.api.projetocontas.domain.entities.ContaCorrente;
import com.br.contas.api.projetocontas.domain.repositories.ContaCorrenteRepository;

public class ValidaContaCorrenteAtivaCase {
    final ContaCorrenteRepository contaCorrenteRepository;

    public ValidaContaCorrenteAtivaCase(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void call(ContaCorrente contaCorrente) {
        contaCorrenteRepository.validarContaCorrenteAtiva(contaCorrente);

    }

}
