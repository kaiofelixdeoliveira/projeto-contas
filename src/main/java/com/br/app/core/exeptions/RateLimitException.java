package com.br.app.core.exeptions;

public class RateLimitException extends RuntimeException {

    public RateLimitException(String message) {
        super(message);
    }
}