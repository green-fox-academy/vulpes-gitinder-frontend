package com.greenfox.gitinder.Model.Response;

import com.greenfox.gitinder.Model.User;

public class LoginResponse extends GitinderResponse {

    String gitinderToken;

    public LoginResponse() {
    }

    public LoginResponse(String status, String message, String gitinderToken) {
        super(status, message);
        this.gitinderToken = gitinderToken;
    }

    public String getGitinderToken() {
        return gitinderToken;
    }

    public void setGitinderToken(String gitinderToken) {
        this.gitinderToken = gitinderToken;
    }
}
