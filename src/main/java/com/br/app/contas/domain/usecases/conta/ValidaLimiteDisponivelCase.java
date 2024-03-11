package com.br.app.contas.domain.usecases.conta;


import com.br.app.contas.domain.model.ContaCorrenteModel;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.ContaCorrenteRepository;

public class ValidaLimiteDisponivelCase {
    final ContaCorrenteRepository contaCorrenteRepository;

    public ValidaLimiteDisponivelCase(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void call(String contaCorrente, Double valorTransferencia) {
        contaCorrenteRepository.validarLimiteDisponivel(contaCorrente, valorTransferencia);

    }

}