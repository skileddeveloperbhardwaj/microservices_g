package com.example.account.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String s) {
        super(s);
    }
}
