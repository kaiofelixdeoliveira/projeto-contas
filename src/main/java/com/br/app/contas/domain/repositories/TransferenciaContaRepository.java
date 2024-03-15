package com.br.app.contas.domain.repositories;

import com.br.app.contas.data.client.dto.BacenRequest;
import com.br.app.contas.data.client.dto.CadastroResponse;
import com.br.app.contas.data.entities.Conta;
import com.br.app.contas.domain.model.ContaModel;
import com.br.app.contas.domain.model.TransferenciaModel;
import com.br.app.core.exeptions.ContaNaoEncontradaException;

import java.time.LocalDate;
import java.util.List;

public interface TransferenciaContaRepository {

    void realizarTransferencia(TransferenciaModel transferencia);

    void notificarTransacao(BacenRequest bacenRequest);

    ContaModel atualizarLimite(String numeroConta, Double novoLimite) throws ContaNaoEncontradaException;

    Double getValorTotalTransferidoPorDia(String numeroConta, LocalDate data);

    ContaModel consultaSaldo(String numeroConta);

    boolean validarContaAtiva(String conta);

    void validarLimiteDisponivel(String conta, Double valorTransferencia);

    void validarLimiteDiario(String conta, Double valorTransferencia);

    List<CadastroResponse> obterNomeCliente(String cpf);
}
