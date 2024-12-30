package br.com.meli.helpdesksimapi.dto;

import lombok.Getter;

@Getter
public class ErroResponseDTO {
    private final int status;
    private final String message;

    public ErroResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
