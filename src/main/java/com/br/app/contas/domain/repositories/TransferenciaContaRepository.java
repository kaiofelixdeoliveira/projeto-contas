package com.br.app.contas.domain.repositories;

import com.br.app.contas.domain.model.ContaModel;
import com.br.app.contas.domain.model.TransferenciaModel;

import java.time.LocalDate;

public interface TransferenciaContaRepository {
    String obterNomeCliente(String cpf);

    void realizarTransferencia(TransferenciaModel transferencia);

    void notificarTransacao(TransferenciaModel transferencia);

    void atualizarLimite(String numeroConta, Double saldo);

    Double getValorTotalTransferidoPorDia(String numeroConta, LocalDate data);

    ContaModel consultaSaldo(String numeroConta);

    boolean validarContaAtiva(String conta);

    boolean validarLimiteDisponivel(String conta, Double valorTransferencia);
}
