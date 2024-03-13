package com.br.app.contas.domain.usecases.bacen;


import com.br.app.contas.domain.repositories.TransferenciaContaRepository;
import com.br.app.contas.domain.model.TransferenciaModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NotificaTransacaoBacenCase {

    private final TransferenciaContaRepository transferenciaContaService;

    public void call(TransferenciaModel transferencia) {
        transferenciaContaService.notificarTransacao(transferencia);

    }

}