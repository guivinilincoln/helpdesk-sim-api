package br.com.meli.helpdesksimapi.dto;

public class SuccessResponse <T>{
    private int status;
    private String message;
    private T data;

    public SuccessResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
