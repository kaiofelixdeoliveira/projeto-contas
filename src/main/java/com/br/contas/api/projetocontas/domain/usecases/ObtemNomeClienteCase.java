package com.br.contas.api.projetocontas.domain.usecases;

import com.br.contas.api.projetocontas.domain.entities.Cliente;
import com.br.contas.api.projetocontas.domain.repositories.ClienteRepository;

public class ObtemNomeClienteCase {
    final ClienteRepository clienteRepository;

    public ObtemNomeClienteCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void call(Cliente cliente) {
        clienteRepository.obterNomeDoCliente(cliente);

    }

}
