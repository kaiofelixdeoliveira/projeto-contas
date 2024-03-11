package com.br.app.contas.data.entities;


import com.br.app.contas.data.enums.StatusTransferencia;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencias")
@AllArgsConstructor
public class TransferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String contaOrigem;
    private String contaDestino;
    private Double valor;
    private StatusTransferencia status;

    private LocalDateTime dataHora;

    // ... getters and setters

}