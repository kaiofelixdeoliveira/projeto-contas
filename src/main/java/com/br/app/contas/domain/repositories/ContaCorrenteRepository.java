package com.br.app.contas.domain.repositories;


import com.br.app.contas.domain.model.ContaCorrenteModel;
import com.br.app.contas.domain.model.TransferenciaModel;

public interface ContaCorrenteRepository {
    ContaCorrenteModel consultaSaldo(String numeroConta);

    boolean validarContaCorrenteAtiva(String contaCorrente);

    boolean validarLimiteDisponivel(String contaCorrente, Double valorTransferencia);
    void atualizarLimite(String contaOrigem, Double valor);


}