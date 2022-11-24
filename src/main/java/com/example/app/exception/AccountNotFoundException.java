package com.example.app.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
    }
    public AccountNotFoundException(String message) {
        super(message);
    }
}
