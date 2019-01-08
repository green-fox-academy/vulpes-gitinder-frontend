package com.greenfox.gitinder.model;

import com.google.gson.annotations.SerializedName;

public class User {

    String username;

    @SerializedName("access_token")
    String accessToken;

    public User(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
