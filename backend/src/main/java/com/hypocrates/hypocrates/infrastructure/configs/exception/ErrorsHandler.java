package com.hypocrates.hypocrates.infrastructure.configs.exception;

import com.hypocrates.hypocrates.application.exception.ServiceException;
import com.hypocrates.hypocrates.application.useCase.InteractError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object> >handleValidationErrors (MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getFieldErrors().stream().map(this::getValidateErrorMessage).toList();
        return ResponseEntity.badRequest().body(Map.of("errors", errors));
    }

    private Map<String, String> getValidateErrorMessage(FieldError fieldError) {
        return Map.of("name", fieldError.getField(), "message",     Objects.requireNonNull(fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(InteractError.class)
    public ResponseEntity<Map<String, Object>> handleInteractError (InteractError interactError) {
        return ResponseEntity.badRequest().body(Map.of(
           "errors", List.of(interactError.getMessage()), "errorCode", interactError.getCode()
        ));
    }

    @ExceptionHandler(EntityFoundSchema.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundSchema (EntityFoundSchema notFoundSchema) {
        return ResponseEntity.badRequest().body(Map.of(
                "errors", List.of(notFoundSchema.getMessage())
        ));
    }

    @ExceptionHandler(DataNotUnique.class)
    public ResponseEntity<Map<String, Object>> handleDataNotUnique (DataNotUnique dataNotUnique) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(dataNotUnique.getMessage())));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, Object>> handleServiceException (ServiceException serviceException) {
        return ResponseEntity.badRequest().body(Map.of("errors", List.of(serviceException.getMessage())));
    }
}
