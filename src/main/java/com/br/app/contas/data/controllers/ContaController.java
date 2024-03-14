package com.br.app.contas.data.controllers;

import com.br.app.contas.data.controllers.dto.ContaDTO;
import com.br.app.contas.domain.model.ContaModel;
import com.br.app.contas.domain.usecases.conta.ConsultaSaldoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    final ConsultaSaldoCase consultarSaldo ;

    ContaController(ConsultaSaldoCase consultarSaldo) {
        this.consultarSaldo = consultarSaldo;
    }

    @GetMapping("/saldo")
    public ResponseEntity<Double> consultarSaldo(@RequestBody ContaDTO conta) {

        var contaModel = ContaDTO.contaDTOToContaModel(conta);
        ContaModel contaSaldo = consultarSaldo.call(contaModel);
        return ResponseEntity.ok(contaSaldo.getSaldo());
    }
}