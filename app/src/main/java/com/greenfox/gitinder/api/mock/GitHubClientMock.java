package com.greenfox.gitinder.api.mock;

import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.service.GitHubClient;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubClientMock implements GitHubClient {
    @Override
    public GitHubCallMock getToken(String ClientID, String secret, final String code) {
        return new GitHubCallMock() {
            @Override
            public void enqueue(Callback<GitHubToken> callback) {
                if (code.equals("7fd23c00de517e3b78c2")) {
                    callback.onResponse(this, Response.success(new GitHubToken("aaa", "bbb")));
                }
            }
        };
    }
}
