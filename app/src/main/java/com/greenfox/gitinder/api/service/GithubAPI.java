package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.model.GitHubUsername;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GithubAPI {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    Call<GitHubToken> getToken(@Query("client_id") String ClientID, @Query("client_secret") String secret, @Query("code") String code);

    @Headers("Accept: application/json")
    @GET("user")
    Call<GitHubUsername> getGitHubUsername(@Header("Authorization") String gitHubToken);
}
