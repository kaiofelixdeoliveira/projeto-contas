package com.br.app.contas.domain.model;


import lombok.Data;


@Data
public class ClienteModel {

    private Long id;

    private String cpf;

    private String nome;

    // ... getters and setters

}