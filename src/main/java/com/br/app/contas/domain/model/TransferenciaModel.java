package com.br.app.contas.domain.model;


import com.br.app.contas.data.entities.Conta;
import com.br.app.contas.data.entities.Transferencia;
import com.br.app.contas.data.enums.StatusTransferencia;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TransferenciaModel {

    private Long id;
    private String cpf;
    private ContaModel contaOrigem;
    private ContaModel contaDestino;
    private Double valor;
    private String status;
    private LocalDate dataHora;

    public static Transferencia transferenciaModelToTransferencia(TransferenciaModel transferenciaModel) {
        var mapper = new ModelMapper();
        Transferencia transferencia = mapper.map(transferenciaModel, Transferencia.class);
        return transferencia;
    }

    public static List<TransferenciaModel> transferenciaToTransferenciaModel(List<Transferencia> transferencia) {
        var mapper = new ModelMapper();
        List<TransferenciaModel> transferenciaModelList = mapper.map(transferencia, new TypeToken<List<TransferenciaModel>>() {}.getType());
        return transferenciaModelList;
    }

}