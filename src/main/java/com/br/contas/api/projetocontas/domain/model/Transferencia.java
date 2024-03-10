package com.br.contas.api.projetocontas.domain.model;

import com.br.contas.api.projetocontas.data.enums.StatusTransferencia;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class Transferencia {

    private Long id;
    private String cpf;
    private String contaCorrente;
    private Double valor;
    private StatusTransferencia status;

    private LocalDateTime dataHora;

    // ... getters and setters

}

