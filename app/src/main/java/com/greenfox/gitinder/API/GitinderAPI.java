package com.greenfox.gitinder.api;//package gitinder.backup.features.gitinder.clients;

import com.greenfox.gitinder.Model.Response.GitinderResponse;
import com.greenfox.gitinder.Model.Response.LoginResponse;
import com.greenfox.gitinder.Model.Settings;
import com.greenfox.gitinder.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface GitinderAPI {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    Call<com.greenfox.gitinder.api.model.GitHubToken> getToken(@Query("client_id") String ClientID, @Query("client_secret") String secret, @Query("code") String code);

    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @DELETE("logout")
    Call<GitinderResponse> logoutUser(@Header("X-Gitinder-Token") String gitinderToken);

    @GET("settings")
    Call<Settings> getSettings(@Header("X-Gitinder-Token") String gitinderToken);

    @PUT("settings")
    Call<GitinderResponse> updateSettings(@Header("X-Gitinder-Token") String gitinderToken, @Body Settings settings);
}
