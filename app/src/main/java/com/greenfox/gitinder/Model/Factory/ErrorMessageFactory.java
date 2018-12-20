package com.greenfox.gitinder.model.Factory;

public class ErrorMessageFactory {

    public String getErrorJSON(String message){
        return "{\"status\": \"error\",\"message\": \"" + message + "\"}";
    }
}
