package com.br.app.contas.domain.usecases.conta;


import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import com.br.app.contas.domain.model.ContaModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ConsultaSaldoCase {

    private final TransferenciaContaRepository transferenciaContaService;


    public ContaModel call(String numeroConta) {
        return transferenciaContaService.consultaSaldo(numeroConta);

    }

}