package com.br.app.contas.domain.usecases.conta;


import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidaContaAtivaCase {
    private final TransferenciaContaRepository transferenciaContaService;

    public void call(String conta) {
        transferenciaContaService.validarContaAtiva(conta);

    }

}