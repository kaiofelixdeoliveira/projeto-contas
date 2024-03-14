package com.br.app.core.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errors; // Optional for validation errors

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }


    // Getters, setters, and constructors (optional)

}