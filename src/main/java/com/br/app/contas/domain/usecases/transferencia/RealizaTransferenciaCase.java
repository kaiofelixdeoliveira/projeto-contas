package com.br.app.contas.domain.usecases.transferencia;


import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RealizaTransferenciaCase {

    private final TransferenciaContaRepository transferenciaContaService;

    public void call(TransferenciaModel transferencia) {
        transferenciaContaService.realizarTransferencia(transferencia);

    }

}