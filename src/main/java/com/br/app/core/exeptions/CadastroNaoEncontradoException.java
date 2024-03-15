package com.br.app.core.exeptions;

public class CadastroNaoEncontradoException extends RuntimeException {

    public CadastroNaoEncontradoException(String message) {
        super(message);
    }
}
