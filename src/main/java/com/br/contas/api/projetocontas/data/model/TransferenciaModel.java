package com.br.contas.api.projetocontas.data.model;

import com.br.contas.api.projetocontas.data.enums.StatusTransferencia;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Table(name = "transferencias")
@Data
public class TransferenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String contaCorrente;
    private Double valor;
    private StatusTransferencia status;

    private LocalDateTime dataHora;

    // ... getters and setters

}

