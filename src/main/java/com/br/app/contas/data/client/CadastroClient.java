package com.br.app.contas.data.client;

import com.br.app.contas.data.client.dto.CadastroResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cadastro-client", url = "${url.cadastro.cliente}")
public interface CadastroClient {

    @GetMapping(produces = "application/json",consumes = "application/json")
    List<CadastroResponse> obterNomeCliente(@RequestParam("cpf") String cpf);

}