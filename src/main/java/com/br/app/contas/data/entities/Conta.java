package com.br.app.contas.data.entities;


import com.br.app.contas.data.enums.StatusConta;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "contas")
public class Conta {

    public Conta(String contaOrigem, double v, StatusConta ativa) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "contaOrigem")
    private List<Transferencia> transferenciasEnviadas;

    @OneToMany(mappedBy = "contaDestino")
    private List<Transferencia> transferenciasRecebidas;

    private String numeroConta;

    private Double saldo;

    private String status;

    public Conta(Long id, Cliente cliente, List<Transferencia> transferenciasEnviadas, List<Transferencia> transferenciasRecebidas, String numeroConta, Double saldo, String status) {
        this.id = id;
        this.cliente = cliente;
        this.transferenciasEnviadas = transferenciasEnviadas;
        this.transferenciasRecebidas = transferenciasRecebidas;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Transferencia> getTransferenciasEnviadas() {
        return transferenciasEnviadas;
    }

    public void setTransferenciasEnviadas(List<Transferencia> transferenciasEnviadas) {
        this.transferenciasEnviadas = transferenciasEnviadas;
    }

    public List<Transferencia> getTransferenciasRecebidas() {
        return transferenciasRecebidas;
    }

    public void setTransferenciasRecebidas(List<Transferencia> transferenciasRecebidas) {
        this.transferenciasRecebidas = transferenciasRecebidas;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}