package com.br.app.contas.domain.usecases.conta;


import com.br.app.contas.domain.model.ContaCorrenteModel;
import com.br.app.contas.domain.repositories.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaSaldoCase {

    @Autowired
    final ContaCorrenteRepository contaCorrenteRepository;

    public ConsultaSaldoCase(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public ContaCorrenteModel call(ContaCorrenteModel contaCorrente) {
        return contaCorrenteRepository.consultaSaldo(contaCorrente.getNumeroConta());

    }

}