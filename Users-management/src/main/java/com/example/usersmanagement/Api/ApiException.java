package com.example.usersmanagement.Api;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
