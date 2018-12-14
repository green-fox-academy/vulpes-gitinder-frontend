package com.greenfox.gitinder.Clients;

import com.greenfox.gitinder.Model.GitHubToken;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubClientMock implements GitHubClient {
    @Override
    public Call<GitHubToken> getToken(String ClientID, String secret, final String code) {
        return new Call<GitHubToken>() {
            @Override
            public Response<GitHubToken> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<GitHubToken> callback) {
                if (code.equals("7fd23c00de517e3b78c2")) {
                    callback.onResponse(this, Response.success(new GitHubToken("aaa", "bbb")));
                }
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<GitHubToken> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
}
