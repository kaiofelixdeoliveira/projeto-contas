package com.br.app.contas.data.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "contas_correntes")
@AllArgsConstructor
public class ContaCorrenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroConta;

    private Double saldo;

    private Boolean ativa;
}