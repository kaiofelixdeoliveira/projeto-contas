package com.br.app.contas.domain.model;


import lombok.Data;


@Data
public class ContaCorrenteModel {

    private Long id;

    private String numeroConta;

    private Double saldo;

    private Boolean ativa;
}