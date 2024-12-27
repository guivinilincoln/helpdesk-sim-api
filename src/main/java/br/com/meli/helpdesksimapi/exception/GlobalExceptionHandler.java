package br.com.meli.helpdesksimapi.exception;

import br.com.meli.helpdesksimapi.dto.ErroResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErroResponseDTO errorResponse = new ErroResponseDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponseDTO> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErroResponseDTO errorResponse = new ErroResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErroResponseDTO> handleInvalidDataException(InvalidDataException ex) {
        ErroResponseDTO errorResponse = new ErroResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponseDTO> IllegalArgumentException(IllegalArgumentException ex) {
        ErroResponseDTO errorResponse = new ErroResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TestCustomValidationException.class)
    public ResponseEntity<ErroResponseDTO> handleCustomValidationException(TestCustomValidationException ex) {
        // Constrói uma mensagem consolidada para todos os campos com erro
        String errorMessage = ex.getFieldErrors().entrySet().stream()
                .map(entry -> "Existe um campo com erro [" + entry.getKey() + "]: " + entry.getValue())
                .collect(Collectors.joining("; "));

        // Criação da resposta de erro
        ErroResponseDTO errorResponse = new ErroResponseDTO(HttpStatus.BAD_REQUEST.value(), errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // Construa o mapa de erros de campo
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacement) -> existing));

        // Construa a mensagem de erro concatenada
        String errorMessage = fieldErrors.entrySet().stream()
                .map(entry -> "Existe um campo com erro [" + entry.getKey() + "]: " + entry.getValue())
                .collect(Collectors.joining("; "));

        ErroResponseDTO errorResponse = new ErroResponseDTO(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
