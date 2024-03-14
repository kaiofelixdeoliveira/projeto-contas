package com.br.app.contas.data.controllers.dto;


import com.br.app.contas.domain.model.ContaModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class ContaDTO {

    private String numero;


    public static ContaModel contaDTOToContaModel(ContaDTO contaDTO) {
        var mapper = new ModelMapper();
        ContaModel contaModel = mapper.map(contaDTO, ContaModel.class);
        return contaModel;
    }
}