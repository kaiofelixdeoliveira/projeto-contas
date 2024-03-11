package com.br.app.contas.domain.usecases.conta;


import com.br.app.contas.domain.model.ContaCorrenteModel;
import com.br.app.contas.domain.repositories.ContaCorrenteRepository;

public class ValidaContaCorrenteAtivaCase {
    final ContaCorrenteRepository contaCorrenteRepository;

    public ValidaContaCorrenteAtivaCase(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void call(String contaCorrente) {
        contaCorrenteRepository.validarContaCorrenteAtiva(contaCorrente);

    }

}