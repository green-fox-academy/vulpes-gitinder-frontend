package com.greenfox.gitinder.Model.Response;

public class GitinderResponse {

    String status;
    String message;

    public GitinderResponse() {
    }

    public GitinderResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorJSON(String message){
        return "{\"status\": \"error\",\"message\": \"" + message + "\"}";
    }
}
