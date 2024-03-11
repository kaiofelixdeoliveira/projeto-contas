package com.br.app.contas.data.controllers.dto;


import com.br.app.contas.data.enums.StatusTransferencia;
import com.br.app.contas.domain.model.TransferenciaModel;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class TransferenciaDTO {

    private Long id;
    private String cpf;
    private ContaCorrenteDTO contaOrigem;
    private ContaCorrenteDTO contaDestino;
    private Double valor;
    private StatusTransferencia status;
    private LocalDateTime dataHora;

    public static TransferenciaModel transferenciaDTOToTransferenciaModel(TransferenciaDTO transferenciaDTO) {
        var mapper = new ModelMapper();
        TransferenciaModel transferenciaModel = mapper.map(transferenciaDTO, TransferenciaModel.class);
        return transferenciaModel;
    }

}