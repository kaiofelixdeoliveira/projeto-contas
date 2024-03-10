package com.br.contas.api.projetocontas.domain.usecases;

import com.br.contas.api.projetocontas.domain.entities.ContaCorrente;
import com.br.contas.api.projetocontas.domain.repositories.ContaCorrenteRepository;

public class ConsultaSaldoCase {

    final ContaCorrenteRepository contaCorrenteRepository;

    public ConsultaSaldoCase(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public ContaCorrente call(ContaCorrente contaCorrente) {
        return contaCorrenteRepository.consultaSaldo(contaCorrente.getNumero());

    }

}
