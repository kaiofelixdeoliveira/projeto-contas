package com.br.app.contas.domain.usecases.bacen;


import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.contas.domain.repositories.TransferenciaRepository;

public class NotificaTransacaoBacenCase {

    final TransferenciaRepository transferenciaRepository;

    public NotificaTransacaoBacenCase(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public void call(TransferenciaModel transferencia) {
        transferenciaRepository.notificarTransacao(transferencia);

    }

}