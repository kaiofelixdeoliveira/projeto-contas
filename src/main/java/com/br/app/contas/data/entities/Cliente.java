package com.br.app.contas.data.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    public Cliente() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String nome;

    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    // ... getters and setters

}