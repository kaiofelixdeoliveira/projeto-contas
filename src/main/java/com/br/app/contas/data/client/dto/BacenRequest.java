package com.br.app.contas.data.client.dto;


import com.br.app.contas.domain.model.TransferenciaModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BacenRequest {

    private String idTransacao;

    private String nome;
    private LocalDateTime dataHora;
    private Double valor;
    private String contaOrigem;
    private String contaDestino;
    private String status;

    public static BacenRequest transferenciaToBacenRequest(TransferenciaModel transferenciaModel) {
        BacenRequest bacenRequest = new BacenRequest();
        bacenRequest.setIdTransacao(UUID.randomUUID().toString());
        bacenRequest.setDataHora(transferenciaModel.getDataHora());
        bacenRequest.setValor(transferenciaModel.getValor());
        bacenRequest.setContaOrigem(transferenciaModel.getContaOrigem().getNumeroConta());
        bacenRequest.setContaDestino(transferenciaModel.getContaDestino().getNumeroConta());
        bacenRequest.setStatus(transferenciaModel.getStatus());
        return bacenRequest;

    }

}
