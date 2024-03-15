package com.br.app.contas.data.controllers;

import com.br.app.contas.data.controllers.dto.ContaDTO;
import com.br.app.contas.domain.model.ContaModel;
import com.br.app.contas.domain.usecases.conta.ConsultaSaldoCase;
import com.br.app.core.exeptions.ApiError;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    final ConsultaSaldoCase consultarSaldo ;

    ContaController(ConsultaSaldoCase consultarSaldo) {
        this.consultarSaldo = consultarSaldo;
    }

    @GetMapping("/saldo/{numeroConta}")
    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    public ResponseEntity<?> consultarSaldo(@PathVariable("numeroConta") String numeroConta) {

        ContaModel contaSaldo = consultarSaldo.call(numeroConta);
        return ResponseEntity.ok(contaSaldo.getSaldo());
    }

    public ResponseEntity<?> fallbackAfterRetry(String numeroConta,Throwable ex) {
        var apiError = new ApiError(422, "all retries have exhausted: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiError.toString());
    }
}