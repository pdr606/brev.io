package com.example.shorturl.services.exceptions;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String code){
        super("Resource not found. Code: " + code);
    }
}
