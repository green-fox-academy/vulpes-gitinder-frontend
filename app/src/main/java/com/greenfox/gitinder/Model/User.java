package com.greenfox.gitinder.Model;

public class User {

    String username;
    String access_token;

    public User(String username, String access_token) {
        this.username = username;
        this.access_token = access_token;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getAccess_token() {
        return access_token;
    }
}
