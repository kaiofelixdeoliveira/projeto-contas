package com.br.contas.api.projetocontas.data.client;

import com.br.contas.api.projetocontas.domain.entities.Transferencia;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "bacen-api")
public interface BacenClient {

    @PostMapping("/transacoes")
    void notificarTransacao(Transferencia transferencia);

}