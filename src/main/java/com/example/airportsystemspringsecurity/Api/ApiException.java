package com.example.airportsystemspringsecurity.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
