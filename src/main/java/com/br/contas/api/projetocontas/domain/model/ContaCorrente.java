package com.br.contas.api.projetocontas.domain.model;


import lombok.Data;

@Data
public class ContaCorrente {

    private Long id;

    private String numero;

    private Double saldo;

    private Boolean ativa;

    // ... getters and setters

}