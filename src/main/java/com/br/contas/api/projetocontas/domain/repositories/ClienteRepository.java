package com.br.contas.api.projetocontas.domain.repositories;

import com.br.contas.api.projetocontas.domain.entities.Cliente;

public interface ClienteRepository {

    public String obterNomeDoCliente(Cliente cliente);
}
