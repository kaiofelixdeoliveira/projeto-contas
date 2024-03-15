package com.br.app.contas.domain.model;


import com.br.app.contas.data.controllers.dto.ContaDTO;
import com.br.app.contas.data.entities.Conta;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class ContaModel {

    private Long id;

    private String numeroConta;

    private Double saldo;

    private String status;

    public static ContaModel contaToContaModel(Conta conta) {
        var mapper = new ModelMapper();
        ContaModel contaModel = mapper.map(conta, ContaModel.class);
        return contaModel;
    }
}