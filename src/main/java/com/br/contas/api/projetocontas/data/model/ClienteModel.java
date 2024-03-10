package com.br.contas.api.projetocontas.data.model;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "clientes")
@Data
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String nome;

    // ... getters and setters

}