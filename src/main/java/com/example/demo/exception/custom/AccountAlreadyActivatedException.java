package com.example.demo.exception.custom;

public class AccountAlreadyActivatedException extends RuntimeException{

    public AccountAlreadyActivatedException(String message) {
        super(message);
    }
}
