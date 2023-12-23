package com.miscroservices.loans.exception;

public class LoansAlreadyExistingException extends RuntimeException {
    public LoansAlreadyExistingException(String msg) {
        super(msg);
    }
}
