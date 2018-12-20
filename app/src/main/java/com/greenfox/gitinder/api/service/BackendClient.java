package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.api.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface BackendClient {

    @Headers("Accept: application/json")
    @GET("profile")
    Call<Profile> getProfile(@Header("X-Gitinder-Token") String token);
    @Headers("Accept: application/json")
    @GET("available")
    Call<AvailableProfiles> getAvailable(@Header("X-Gitinder-Token") String token);



}
