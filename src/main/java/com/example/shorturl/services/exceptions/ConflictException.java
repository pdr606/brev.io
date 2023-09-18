package com.example.shorturl.services.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String code){
        super("Conflict, this code already exist. Code: " + code );
    }
}
