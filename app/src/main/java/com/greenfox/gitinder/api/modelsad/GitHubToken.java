package com.greenfox.gitinder.api.modelsad;

import com.google.gson.annotations.SerializedName;

public class GitHubToken {

    @SerializedName("access_token")
    String token;
    String scope;
    @SerializedName("token_type")
    String tokenType;

    public GitHubToken(String token, String tokenType) {
        scope = "";
        this.token = token;
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }
}
