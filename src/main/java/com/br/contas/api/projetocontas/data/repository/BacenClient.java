package com.br.contas.api.projetocontas.data.repository;

import com.br.contas.api.projetocontas.domain.model.Transferencia;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "bacen-api")
public interface BacenClient {

    @PostMapping("/transacoes")
    void notificarTransacao(Transferencia transferencia);

}