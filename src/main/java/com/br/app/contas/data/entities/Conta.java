package com.br.app.contas.data.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;


@Entity
@Table(name = "contas")
@AllArgsConstructor
public class Conta {

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

    private Boolean ativa;
}