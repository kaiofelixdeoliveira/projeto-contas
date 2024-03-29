package com.br.app.contas.data.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transferencias")
public class Transferencia {

    public Transferencia() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "contaOrigem")
    private Conta contaOrigem;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "contaDestino")
    private Conta contaDestino;
    private Double valor;
    private String status;

    private LocalDate dataHora;

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

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    // ... getters and setters

}