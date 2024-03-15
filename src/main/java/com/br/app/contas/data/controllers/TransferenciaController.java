package com.br.app.contas.data.controllers;


import com.br.app.contas.data.controllers.dto.TransferenciaDTO;
import com.br.app.contas.domain.usecases.transferencia.RealizaTransferenciaCase;
import com.br.app.core.exeptions.ApiError;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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


    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> realizarTransferencia(@Valid @RequestBody TransferenciaDTO transferenciaDTO) {

        var transferenciaModel = TransferenciaDTO.transferenciaDTOToTransferenciaModel(transferenciaDTO);
        realizaTransferencia.call(transferenciaModel);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<?> fallbackAfterRetry(TransferenciaDTO transferenciaDTO, Throwable ex) {
        var apiError = new ApiError(422, "all retries have exhausted: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiError);

    }


}