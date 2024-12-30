package br.com.meli.helpdesksimapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class SuccessResponseDTO<T>{
    private int status;
    private String message;
    private T data;

}
