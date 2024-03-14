package com.br.app.contas.data.controllers;


import com.br.app.contas.data.controllers.dto.TransferenciaDTO;
import com.br.app.contas.domain.usecases.transferencia.RealizaTransferenciaCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private RealizaTransferenciaCase realizaTransferencia;



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> realizarTransferencia(@Valid @RequestBody TransferenciaDTO transferenciaDTO) {

        var transferenciaModel = TransferenciaDTO.transferenciaDTOToTransferenciaModel(transferenciaDTO);
        realizaTransferencia.call(transferenciaModel);
        return ResponseEntity.ok().build();

    }


}