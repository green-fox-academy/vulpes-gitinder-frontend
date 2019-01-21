package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.api.model.GitHubUsername;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GithubAPI {

    @Headers("Accept: application/json")
    @GET("user")
    Call<GitHubUsername> getGitHubUsername(@Header("Authorization") String gitHubToken);
}
