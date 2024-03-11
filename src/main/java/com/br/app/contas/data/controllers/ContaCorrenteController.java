package com.br.app.contas.data.controllers;

import com.br.app.contas.data.controllers.dto.ContaCorrenteDTO;
import com.br.app.contas.domain.model.ContaCorrenteModel;
import com.br.app.contas.domain.usecases.conta.ConsultaSaldoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/contas-correntes")
public class ContaCorrenteController {

    @Autowired
    final ConsultaSaldoCase consultarSaldo ;

    ContaCorrenteController(ConsultaSaldoCase consultarSaldo) {
        this.consultarSaldo = consultarSaldo;
    }

    @GetMapping("/saldo")
    public ResponseEntity<Double> consultarSaldo(@RequestBody ContaCorrenteDTO contaCorrente) {

        var contaCorrenteModel = ContaCorrenteDTO.contaCorrenteDTOToContaCorrenteModel(contaCorrente);
        ContaCorrenteModel contaCorrenteSaldo = consultarSaldo.call(contaCorrenteModel);
        return ResponseEntity.ok(contaCorrenteSaldo.getSaldo());
    }
}