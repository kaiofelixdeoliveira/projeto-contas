package com.br.contas.api.projetocontas.domain.usecases;

import com.br.contas.api.projetocontas.domain.entities.ContaCorrente;
import com.br.contas.api.projetocontas.domain.entities.Transferencia;
import com.br.contas.api.projetocontas.domain.repositories.ContaCorrenteRepository;

public class ValidaLimiteDisponivelCase {
    final ContaCorrenteRepository contaCorrenteRepository;

    public ValidaLimiteDisponivelCase(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void call(ContaCorrente contaCorrente, Transferencia transferencia) {
        contaCorrenteRepository.validarLimiteDisponivel(contaCorrente, transferencia);

    }

}
