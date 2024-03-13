package com.br.app.contas.data.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "conta-api", url = "${url.conta}")
public interface ContaClient {

    @GetMapping("/contas/{conta}/ativa")
    void validarContaAtiva(@PathVariable String conta);

    @GetMapping("/contas/{conta}/limite-disponivel")
    void validarLimiteDisponivel(@PathVariable String conta, @RequestParam Double valor);

}