package com.greenfox.gitinder.Model;

import com.google.gson.annotations.SerializedName;

public class GitHubToken {

    @SerializedName("acces_token")
    String token;
    String scope;
    @SerializedName("token_type")
    String tokenType;

    public String getToken() {
        return token;
    }
}
