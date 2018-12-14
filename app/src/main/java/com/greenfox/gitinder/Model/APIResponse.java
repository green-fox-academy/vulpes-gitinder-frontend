package com.greenfox.gitinder.Model;

public class APIResponse {

    String username;
    String access_token;
    String status;
    String gitinder_token;
    String message;

    public APIResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGitinder_token() {
        return gitinder_token;
    }

    public void setGitinder_token(String gitinder_token) {
        this.gitinder_token = gitinder_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorJSON(){
        return "{\"status\": \"error\",\"message\": \"Access token is missing!\"}";
    }
}
