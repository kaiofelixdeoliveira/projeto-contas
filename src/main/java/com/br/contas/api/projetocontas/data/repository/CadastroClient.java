package com.br.contas.api.projetocontas.data.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cadastro-client", url = "${url.cadastro.cliente}")
public interface CadastroClient {

    @GetMapping("/clientes/{cpf}")
    String obterNomeCliente(@PathVariable String cpf);

}
