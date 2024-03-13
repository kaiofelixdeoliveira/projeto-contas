package com.br.app.contas.domain.usecases.conta;


import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidaLimiteDisponivelCase {
    private final TransferenciaContaRepository transferenciaContaService;

    public void call(String conta, Double valorTransferencia) {
        transferenciaContaService.validarLimiteDisponivel(conta, valorTransferencia);

    }

}