package com.example.demo.exception.custom;

public class JobExpiredException extends RuntimeException{

    public JobExpiredException(String message) {
        super(message);
    }
}
