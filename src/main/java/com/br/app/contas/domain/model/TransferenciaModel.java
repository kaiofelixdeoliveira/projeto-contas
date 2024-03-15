package com.br.app.contas.domain.model;


import com.br.app.contas.data.entities.Conta;
import com.br.app.contas.data.entities.Transferencia;
import com.br.app.contas.data.enums.StatusTransferencia;
import lombok.Data;
import org.modelmapper.ModelMapper;


import java.time.LocalDateTime;

@Data
public class TransferenciaModel {

    private Long id;
    private String cpf;
    private ContaModel contaOrigem;
    private ContaModel contaDestino;
    private Double valor;
    private String status;
    private LocalDateTime dataHora;

    public static Transferencia transferenciaModelToTransferencia(TransferenciaModel transferenciaModel) {
        var mapper = new ModelMapper();
        Transferencia transferencia = mapper.map(transferenciaModel, Transferencia.class);
        return transferencia;
    }

}