package com.greenfox.gitinder.Model.Factory;

public class ErrorMessageFactory {

    public String getErrorJSON(String message){
        return "{\"status\": \"error\",\"message\": \"" + message + "\"}";
    }
}
