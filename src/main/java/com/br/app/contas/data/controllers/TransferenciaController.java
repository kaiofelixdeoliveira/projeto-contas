package com.br.app.contas.data.controllers;


import com.br.app.contas.data.controllers.dto.TransferenciaDTO;
import com.br.app.contas.domain.usecases.transferencia.RealizaTransferenciaCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    final RealizaTransferenciaCase realizaTransferencia;

    TransferenciaController(RealizaTransferenciaCase realizaTransferencia) {
        this.realizaTransferencia = realizaTransferencia;
    }


    @PostMapping
    public ResponseEntity<?> realizarTransferencia(@RequestBody TransferenciaDTO transferenciaDTO) {

        var transferenciaModel = TransferenciaDTO.transferenciaDTOToTransferenciaModel(transferenciaDTO);
        realizaTransferencia.call(transferenciaModel);
        return ResponseEntity.ok().build();

    }


}