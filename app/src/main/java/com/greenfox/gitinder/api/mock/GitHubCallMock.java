package com.greenfox.gitinder.api.mock;

import com.greenfox.gitinder.api.model.GitHubToken;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class GitHubCallMock implements Call<GitHubToken> {
    @Override
    public Response<GitHubToken> execute() throws IOException {
        return null;
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
}
