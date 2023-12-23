package com.microservices.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class CardsAlreadyExistedException extends RuntimeException{
    public CardsAlreadyExistedException(String msg) {
        super(msg);
    }
}
