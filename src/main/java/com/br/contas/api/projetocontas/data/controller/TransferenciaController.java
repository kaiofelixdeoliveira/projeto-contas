package com.br.contas.api.projetocontas.data.controller;

import com.br.contas.api.projetocontas.core.exception.ApiError;
import com.br.contas.api.projetocontas.core.exception.LimiteDiarioExcedidoException;
import com.br.contas.api.projetocontas.domain.model.Transferencia;
import com.br.contas.api.projetocontas.domain.usecases.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private  TransferenciaService transferenciaService;


    @PostMapping
    public ResponseEntity<?> realizarTransferencia(@RequestBody Transferencia transferencia) {

        try {
            transferenciaService.realizarTransferencia(transferencia);
            return ResponseEntity.created(URI.create("/transferencias/" + transferencia.getId())).build();
        } catch (LimiteDiarioExcedidoException e) {
            return ResponseEntity.badRequest().body(new ApiError("Limite diário de R$ 1.000,00 excedido"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError("Falha ao realizar transferência"));
        }

    }


}