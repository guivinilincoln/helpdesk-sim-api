package br.com.meli.helpdesksimapi.dto;

public class ErroResponse {
    private int status;
    private String message;

    public ErroResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
