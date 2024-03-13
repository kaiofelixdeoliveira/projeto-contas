package com.br.app.contas.data.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String nome;

    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas;

    // ... getters and setters

}