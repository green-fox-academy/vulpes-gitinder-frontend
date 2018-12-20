package com.greenfox.gitinder.model.factory;

public class ErrorMessageFactory {

    public String getErrorJSON(String message){
        return "{\"status\": \"error\",\"message\": \"" + message + "\"}";
    }
}
