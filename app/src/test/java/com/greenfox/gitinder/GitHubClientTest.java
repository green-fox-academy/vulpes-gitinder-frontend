package com.greenfox.gitinder;

import com.greenfox.gitinder.Model.APIResponse;
import com.greenfox.gitinder.Model.Profile;
import com.greenfox.gitinder.Model.User;
import com.greenfox.gitinder.clients.GitHubClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class GitHubClientTest {

    @Test
    public void loginPostGotAllParamsTest(){
        User testUser = new User("Ferdinand", "fakink123");

        GitHubClient client = new MockService();
        Call<APIResponse> call = client.usernameAndToken(testUser);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                assertEquals(200, response.code());
                assertEquals("abc123", response.body().getGitinder_token());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {}
        });
    }

    @Test
    public void loginPostMissingParamTest(){
        User testUser = new User("Ferdinand");
        final APIResponse apiResponse = new APIResponse();

        GitHubClient client = new MockService();
        Call<APIResponse> call = client.usernameAndToken(testUser);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                assertEquals(400, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Access token is missing!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {}
        });
    }

    @Test
    public void loginDeleteHeaderIsCorrectTest(){
        GitHubClient client = new MockService();
        Call<APIResponse> call = client.logoutUser("abc123");

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                assertEquals(200, response.code());
                assertEquals("Logged out successfully!",response.body().getMessage());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void loginDeleteHeaderIsEmptyTest(){
        final APIResponse apiResponse = new APIResponse();

        GitHubClient client = new MockService();
        Call<APIResponse> call = client.logoutUser("");

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void loginDeleteHeaderIsNullTest(){
        final APIResponse apiResponse = new APIResponse();

        GitHubClient client = new MockService();
        Call<APIResponse> call = client.logoutUser(null);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                            response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void profileGetHeaderIsCorrectTest(){
        GitHubClient client = new MockService();
        Call<Profile> call = client.getUserProfile("abc123");
        Profile profileService = new Profile();
        final Profile testJerry = profileService.createProfile("Jerry");

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                assertEquals(200, response.code());
                assertEquals(testJerry.getUsername(), response.body().getUsername());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    @Test
    public void profileGetHeaderIsIncorrectTest(){
        GitHubClient client = new MockService();
        Call<Profile> call = client.getUserProfile("");
        final APIResponse apiResponse = new APIResponse();

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {}
        });
    }

}
