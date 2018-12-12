package com.greenfox.gitinder.Clients;

import com.greenfox.gitinder.Model.GitHubToken;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GitHubClient {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    Call<GitHubToken> getToken(@Query("client_id") String ClientID, @Query("client_secret") String secret, @Query("code") String code);
}
