package br.com.wano.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IsbnAlreadyExistsException extends RuntimeException{

    public IsbnAlreadyExistsException(String message){
        super(message);
    }
}
