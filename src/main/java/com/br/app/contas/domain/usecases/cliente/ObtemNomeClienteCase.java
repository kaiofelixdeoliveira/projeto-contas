package com.br.app.contas.domain.usecases.cliente;


import com.br.app.contas.domain.model.ClienteModel;
import com.br.app.contas.domain.repositories.ClienteRepository;

public class ObtemNomeClienteCase {
    final ClienteRepository clienteRepository;

    public ObtemNomeClienteCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void call(ClienteModel cliente) {
        clienteRepository.obterNomeDoCliente(cliente);

    }

}