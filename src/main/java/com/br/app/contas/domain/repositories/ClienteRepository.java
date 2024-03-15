package com.br.app.contas.domain.repositories;


import com.br.app.contas.domain.model.ClienteModel;

public interface ClienteRepository {

    public String obterNomeDoCliente(ClienteModel cliente);
}