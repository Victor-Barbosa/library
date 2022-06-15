package br.com.wano.library.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(IsbnAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleIsbnAlreadyExistsExcetion(IsbnAlreadyExistsException exception) {
        Error error = new Error(exception.getMessage(), "Isbn Already Exists");
        List<Error> errors = new ArrayList<>();
        errors.add(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.add(new Error(error.getDefaultMessage(), String.format("Field %s not valid", error.getField())));
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()){
            errors.add(new Error(error.getDefaultMessage(), String.format("Object %s not valid", error.getObjectName())));
        }
        return ResponseEntity.status(status).body(new ErrorResponse(errors));
    }
}
