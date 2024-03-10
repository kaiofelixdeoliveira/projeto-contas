package com.br.contas.api.projetocontas.domain.model;

import lombok.Data;


@Data
public class Cliente {


    private Long id;

    private String cpf;

    private String nome;

    // ... getters and setters

}