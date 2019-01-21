package com.greenfox.gitinder.api.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse extends GitinderResponse {

    @SerializedName("gitinder_token")
    String gitinderToken;

    public LoginResponse() {
        super();
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
