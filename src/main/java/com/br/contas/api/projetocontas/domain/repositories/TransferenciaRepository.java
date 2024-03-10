package com.br.contas.api.projetocontas.domain.repositories;

import com.br.contas.api.projetocontas.domain.entities.Cliente;
import com.br.contas.api.projetocontas.domain.entities.Transferencia;

public interface TransferenciaRepository {
    public void realizarTransferencia(Transferencia transferencia);

    void notificarTransacao(Transferencia transferencia);

}
