package com.br.app.contas.domain.repositories;


import com.br.app.contas.domain.model.TransferenciaModel;

import java.time.LocalDate;

public interface TransferenciaRepository {
     void realizarTransferencia(TransferenciaModel transferencia);

    void notificarTransacao(TransferenciaModel transferencia);
    void atualizarLimite(String numeroConta, Double saldo);

    Double getValorTotalTransferidoPorDia(String contaCorrente, LocalDate date);


}