package com.br.contas.api.projetocontas.data.controller;

import com.br.contas.api.projetocontas.domain.entities.ContaCorrente;
import com.br.contas.api.projetocontas.domain.usecases.ConsultaSaldoCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contas-correntes")
public class ContaCorrenteController {

    final ConsultaSaldoCase consultarSaldo ;

    ContaCorrenteController(ConsultaSaldoCase consultarSaldo) {
        this.consultarSaldo = consultarSaldo;
    }

    @GetMapping("/saldo")
    public ResponseEntity<Double> consultarSaldo(@RequestBody ContaCorrente contaCorrente) {
        ContaCorrente contaCorrenteSaldo = consultarSaldo.call(contaCorrente);
        return ResponseEntity.ok(contaCorrenteSaldo.getSaldo());
    }
}
