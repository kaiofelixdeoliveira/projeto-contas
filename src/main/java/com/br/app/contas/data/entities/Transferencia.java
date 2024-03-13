package com.br.app.contas.data.entities;


import com.br.app.contas.data.enums.StatusTransferencia;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencias")
@AllArgsConstructor
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private ContaCorrente contaOrigem;
    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private ContaCorrente contaDestino;
    private Double valor;
    private StatusTransferencia status;

    private LocalDateTime dataHora;

    // ... getters and setters

}