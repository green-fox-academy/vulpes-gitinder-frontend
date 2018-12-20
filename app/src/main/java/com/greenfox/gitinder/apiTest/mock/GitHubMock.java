package com.greenfox.gitinder.apiTest.mock;

import com.greenfox.gitinder.apiTest.model.GitHubToken;
import com.greenfox.gitinder.apiTest.service.GithubAPI;

import retrofit2.Callback;
import retrofit2.Response;

public class GitHubMock implements GithubAPI {

    @Override
    public CallMock<com.greenfox.gitinder.apiTest.model.GitHubToken> getToken(String ClientID, String secret, final String code) {
        return new CallMock<com.greenfox.gitinder.apiTest.model.GitHubToken>() {
            @Override
            public void enqueue(Callback<GitHubToken> callback) {
                if (code.equals("7fd23c00de517e3b78c2")) {
                    callback.onResponse(this, Response.success(new com.greenfox.gitinder.apiTest.model.GitHubToken("aaa", "bbb")));
                }
            }
        };
    }

}
