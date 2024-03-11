package com.br.app.contas.domain.usecases.transferencia;


import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.TransferenciaRepository;
import org.springframework.stereotype.Component;

@Component
public class RealizaTransferenciaCase {

    final TransferenciaRepository transferenciaRepository;

    public RealizaTransferenciaCase(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public void call(TransferenciaModel transferencia) {
        transferenciaRepository.realizarTransferencia(transferencia);

    }

}