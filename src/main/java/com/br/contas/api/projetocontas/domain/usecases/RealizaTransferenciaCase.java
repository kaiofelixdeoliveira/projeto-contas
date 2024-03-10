package com.br.contas.api.projetocontas.domain.usecases;

import com.br.contas.api.projetocontas.domain.entities.Transferencia;
import com.br.contas.api.projetocontas.domain.repositories.TransferenciaRepository;

public class RealizaTransferenciaCase {

    final TransferenciaRepository transferenciaRepository;

    public RealizaTransferenciaCase(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public void call(Transferencia transferencia) {
        transferenciaRepository.realizarTransferencia(transferencia);

    }

}