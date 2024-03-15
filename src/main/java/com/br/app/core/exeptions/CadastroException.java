package com.br.app.core.exeptions;

public class CadastroException extends RuntimeException {

    private final int statusCode;
    private final String message;

    public CadastroException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
