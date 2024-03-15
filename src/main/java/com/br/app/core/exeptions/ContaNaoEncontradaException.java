package com.br.app.core.exeptions;

public class ContaNaoEncontradaException extends RuntimeException {

    public ContaNaoEncontradaException(String message) {
        super(message);
    }
}