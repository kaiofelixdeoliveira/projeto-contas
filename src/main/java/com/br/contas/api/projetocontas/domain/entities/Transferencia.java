package com.br.contas.api.projetocontas.domain.entities;

import com.br.contas.api.projetocontas.data.enums.StatusTransferencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;

@Data
public class Transferencia {

    private Long id;
    private String cpf;
    private String contaCorrente;
    private Double valor;
    private StatusTransferencia status;

    private LocalDateTime dataHora;

    // ... getters and setters

}

