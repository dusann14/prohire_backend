package com.example.demo.exception.custom;

public class PastDateException extends RuntimeException{
    public PastDateException(String message) {
        super(message);
    }
}
