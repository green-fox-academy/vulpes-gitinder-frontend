package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.model.Response.GitinderResponse;
import com.greenfox.gitinder.model.Response.LoginResponse;
import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GitinderAPI {

    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @DELETE("logout")
    Call<GitinderResponse> logoutUser(@Header("X-Gitinder-Token") String gitinderToken);

    @GET("settings")
    Call<Settings> getSettings(@Header("X-Gitinder-Token") String gitinderToken);

    @PUT("settings")
    Call<GitinderResponse> updateSettings(@Header("X-Gitinder-Token") String gitinderToken, @Body Settings settings);
}
