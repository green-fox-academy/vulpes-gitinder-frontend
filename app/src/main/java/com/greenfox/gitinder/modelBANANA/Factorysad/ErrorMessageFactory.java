package com.greenfox.gitinder.modelBANANA.Factorysad;

public class ErrorMessageFactory {

    public String getErrorJSON(String message){
        return "{\"status\": \"error\",\"message\": \"" + message + "\"}";
    }
}
