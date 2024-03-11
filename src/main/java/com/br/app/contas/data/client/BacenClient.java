package com.br.app.contas.data.client;

import com.br.app.contas.domain.model.TransferenciaModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "bacen-api", url = "${url.bacen}")
public interface BacenClient {

    @PostMapping("/transacoes")
    void notificarTransacao(TransferenciaModel transferencia);

}