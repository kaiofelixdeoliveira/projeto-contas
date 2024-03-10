package com.br.contas.api.projetocontas.domain.repositories;

import com.br.contas.api.projetocontas.domain.entities.ContaCorrente;
import com.br.contas.api.projetocontas.domain.entities.Transferencia;

public interface ContaCorrenteRepository {
    ContaCorrente consultaSaldo(String numeroConta);

    void validarContaCorrenteAtiva(ContaCorrente contaCorrente);

    void validarLimiteDisponivel(ContaCorrente contaCorrente, Transferencia valor);

}
