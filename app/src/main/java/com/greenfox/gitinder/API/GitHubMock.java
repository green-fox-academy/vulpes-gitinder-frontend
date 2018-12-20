package com.greenfox.gitinder.api;

import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.service.GithubAPI;

import retrofit2.Callback;
import retrofit2.Response;

public class GitHubMock implements GithubAPI {

    @Override
    public CallMock<com.greenfox.gitinder.api.model.GitHubToken> getToken(String ClientID, String secret, final String code) {
        return new CallMock<com.greenfox.gitinder.api.model.GitHubToken>() {
            @Override
            public void enqueue(Callback<GitHubToken> callback) {
                if (code.equals("7fd23c00de517e3b78c2")) {
                    callback.onResponse(this, Response.success(new com.greenfox.gitinder.api.model.GitHubToken("aaa", "bbb")));
                }
            }
        };
    }

}
