package com.greenfox.gitinder.clients;//package gitinder.backup.features.gitinder.clients;

import com.greenfox.gitinder.Model.APIResponse;
import com.greenfox.gitinder.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GitHubClient {

    @POST("login")
    Call<APIResponse> usernameAndToken(@Body User user);



}