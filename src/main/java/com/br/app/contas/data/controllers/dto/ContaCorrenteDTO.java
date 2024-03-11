package com.br.app.contas.data.controllers.dto;


import com.br.app.contas.domain.model.ContaCorrenteModel;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class ContaCorrenteDTO {

    private Long id;

    private String numero;


    public static ContaCorrenteModel contaCorrenteDTOToContaCorrenteModel(ContaCorrenteDTO contaCorrenteDTO) {
        var mapper = new ModelMapper();
        ContaCorrenteModel contaCorrenteModel = mapper.map(contaCorrenteDTO, ContaCorrenteModel.class);
        return contaCorrenteModel;
    }
}