package com.blog.mail.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorEntity> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorEntity> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            ErrorEntity errorEntity = new ErrorEntity();
            errorEntity.setField(((FieldError) error).getField());
            errorEntity.setError(error.getDefaultMessage());
            errorEntity.setStatus(String.valueOf(ex.getStatusCode().value()));
            errors.add(errorEntity);
        });
        return errors;
    }
}
