package br.com.meli.helpdesksimapi.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class TestCustomValidationException extends RuntimeException {
    private final Map<String, String> fieldErrors;

    public TestCustomValidationException(Map<String, String> fieldErrors) {
        super("Erro de validação");
        this.fieldErrors = fieldErrors;
    }
}
