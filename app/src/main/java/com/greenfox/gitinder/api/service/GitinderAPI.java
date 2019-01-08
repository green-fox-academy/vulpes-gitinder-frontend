package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.api.model.GitinderResponse;
import com.greenfox.gitinder.api.model.LoginResponse;
import com.greenfox.gitinder.api.model.SwipeResponse;
import com.greenfox.gitinder.model.Matches;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GitinderAPI {

    @POST("login")
    Call<LoginResponse> login(@Body User user);

    @DELETE("logout")
    Call<GitinderResponse> logoutUser(@Header("X-Gitinder-Token") String gitinderToken);

    @GET("settings")
    Call<Settings> getSettings(@Header("X-Gitinder-Token") String gitinderToken);

    @PUT("settings")
    Call<GitinderResponse> updateSettings(@Header("X-Gitinder-Token") String gitinderToken, @Body Settings settings);

    @GET("profile")
    Call<Profile> getProfile(@Header("X-Gitinder-Token") String gitinderToken);

    @GET("available")
    Call<AvailableProfiles> getAvailable(@Header("X-Gitinder-Token") String gitinderToken);

    @PUT("profiles/{username}/{direction}")
    Call<SwipeResponse> swipe (@Header("X-Gitinder-Token") String gitinderToken, @Path("username") String username, @Path("direction") String leftorright);

    @GET("matches")
    Call<Matches> matches (@Header("X-Gitinder-Token") String gitinderToken);
}
