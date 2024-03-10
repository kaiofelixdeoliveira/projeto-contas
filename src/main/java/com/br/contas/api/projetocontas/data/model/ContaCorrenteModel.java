package com.br.contas.api.projetocontas.data.model;


import jakarta.persistence.*;
import lombok.Data;



@Entity
@Table(name = "contas_correntes")
@Data
public class ContaCorrenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private Double saldo;

    private Boolean ativa;
}