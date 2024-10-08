package com.projarc.sarc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento para ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Tratamento para DataIntegrityException
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<String> handleDataIntegrityException(DataIntegrityException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Tratamento para validação de argumentos (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Tratamento para IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Novo Tratamento para HttpMessageNotReadableException (inclui InvalidFormatException)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) cause;
            if (ife.getTargetType().isEnum()) {
                String fieldName = ife.getPath().get(0).getFieldName();
                String invalidValue = ife.getValue().toString();
                String allowedValues = Arrays.toString(ife.getTargetType().getEnumConstants());
                String message = String.format(
                    "Valor inválido '%s' para o campo '%s'. Valores permitidos: %s.",
                    invalidValue, fieldName, allowedValues
                );
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
        }
        // Para outros tipos de erros de leitura de mensagem
        return new ResponseEntity<>("Requisição malformada.", HttpStatus.BAD_REQUEST);
    }

    // Tratamento genérico para outras exceções não previstas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Opcional: Adicionar log para o erro
        // LoggerFactory.getLogger(GlobalExceptionHandler.class).error("Erro interno: ", ex);
        return new ResponseEntity<>("Ocorreu um erro interno.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
