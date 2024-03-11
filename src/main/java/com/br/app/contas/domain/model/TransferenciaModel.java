package com.br.app.contas.domain.model;


import com.br.app.contas.data.enums.StatusTransferencia;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TransferenciaModel {

    private Long id;
    private String cpf;
    private String contaOrigem;
    private String contaDestino;
    private Double valor;
    private StatusTransferencia status;
    private LocalDateTime dataHora;

    // ... getters and setters

}