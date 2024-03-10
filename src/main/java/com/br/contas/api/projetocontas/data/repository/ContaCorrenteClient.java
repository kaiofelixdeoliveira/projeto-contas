package com.br.contas.api.projetocontas.data.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "conta-corrente-api")
public interface ContaCorrenteClient {

    @GetMapping("/contas-correntes/{contaCorrente}/ativa")
    void validarContaCorrenteAtiva(@PathVariable String contaCorrente);

    @GetMapping("/contas-correntes/{contaCorrente}/limite-disponivel")
    void validarLimiteDisponivel(@PathVariable String contaCorrente, @RequestParam Double valor);

}
