package com.br.app.contas.data.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "conta-corrente-api", url = "${url.conta.corrente}")
public interface ContaCorrenteClient {

    @GetMapping("/contas-correntes/{contaCorrente}/ativa")
    void validarContaCorrenteAtiva(@PathVariable String contaCorrente);

    @GetMapping("/contas-correntes/{contaCorrente}/limite-disponivel")
    void validarLimiteDisponivel(@PathVariable String contaCorrente, @RequestParam Double valor);

}