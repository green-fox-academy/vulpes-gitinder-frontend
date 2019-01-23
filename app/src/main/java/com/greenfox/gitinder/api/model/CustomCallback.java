package com.greenfox.gitinder.api.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CustomCallback<T> implements Callback<T> {
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }
}
