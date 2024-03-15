package com.br.app.core.exeptions;

public class ExcedeSaldoDisponivelException extends RuntimeException {

    public ExcedeSaldoDisponivelException() {
        super("valor da transferência excede o saldo disponível");
    }

}