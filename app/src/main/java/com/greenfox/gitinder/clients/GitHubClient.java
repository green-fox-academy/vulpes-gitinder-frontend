package com.greenfox.gitinder.clients;//package gitinder.backup.features.gitinder.clients;

import com.greenfox.gitinder.Model.APIResponse;
import com.greenfox.gitinder.Model.Profile;
import com.greenfox.gitinder.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GitHubClient {

    @POST("login")
    Call<APIResponse> usernameAndToken(@Body User user);

    @DELETE("logout")
    Call<APIResponse> logoutUser(@Header("X-Gitinder-Token") String gitinderToken);

    @GET("profile")
    Call<Profile> getUserProfile(@Header("X-Gitinder-Token") String gitinderToken);


}