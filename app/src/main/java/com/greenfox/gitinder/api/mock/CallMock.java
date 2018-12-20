package com.greenfox.gitinder.api.mock;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

public abstract class CallMock<T> implements Call<T> {
    @Override
    public Response<T> execute() throws IOException {
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
    public Call<T> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
