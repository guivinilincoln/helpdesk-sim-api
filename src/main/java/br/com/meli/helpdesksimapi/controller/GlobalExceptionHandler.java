package br.com.meli.helpdesksimapi.controller;

import br.com.meli.helpdesksimapi.dto.ErroResponse;
import br.com.meli.helpdesksimapi.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErroResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErroResponse errorResponse = new ErroResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErroResponse errorResponse = new ErroResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErroResponse> handleInvalidFormatException(InvalidFormatException e) {
        String fieldName = "(desconhecido)"; // inicialização padrão

        // Itera sobre as referências no caminho do JSON para identificar o campo que causou o erro
        for (JsonMappingException.Reference reference : e.getPath()) {
            fieldName = reference.getFieldName(); // Pega o nome do campo
        }

        String errorMessage;

        if ("atendenteId".equals(fieldName)) {
            // Mensagem personalizada para o campo atendenteId
            errorMessage = "Valor inválido para o ID do atendente. Deve ser um número.";
        } else {
            // Mensagem genérica para outros casos
            errorMessage = "Valor inválido fornecido.";
        }

        ErroResponse errorResponse = new ErroResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
