package com.br.app.contas.domain.usecases.cliente;


import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObtemNomeClienteCase {
    private final TransferenciaContaRepository transferenciaContaService;


    public void call(String cpf) {
        transferenciaContaService.obterNomeCliente(cpf);

    }

}