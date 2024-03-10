package com.br.contas.api.projetocontas.core.exception;

public class LimiteDiarioExcedidoException extends RuntimeException {

    public LimiteDiarioExcedidoException() {
        super("Limite diário de R$ 1.000,00 excedido");
    }

}