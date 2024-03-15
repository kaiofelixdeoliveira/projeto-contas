package com.br.app.contas.data.controllers.dto;


import com.br.app.contas.domain.model.TransferenciaModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;


@Data
public class TransferenciaDTO {

    @NotNull(message = "cpf is mandatory")
    @NotEmpty(message = "cpf is mandatory")
    @JsonProperty("cpf")
    private String cpf;
    @Valid
    @JsonProperty("conta_origem")
    private ContaDTO contaOrigem;

    @Valid
    @JsonProperty("conta_destino")
    private ContaDTO contaDestino;

    @JsonProperty("valor")
    private Double valor;

    @JsonProperty("data_hora")
    private LocalDateTime dataHora;

    public static TransferenciaModel transferenciaDTOToTransferenciaModel(TransferenciaDTO transferenciaDTO) {
        var mapper = new ModelMapper();
        TransferenciaModel transferenciaModel = mapper.map(transferenciaDTO, TransferenciaModel.class);
        return transferenciaModel;
    }

}