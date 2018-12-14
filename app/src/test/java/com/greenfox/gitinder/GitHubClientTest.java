package com.greenfox.gitinder;

import com.greenfox.gitinder.Model.APIResponse;
import com.greenfox.gitinder.Model.Settings;
import com.greenfox.gitinder.Model.User;
import com.greenfox.gitinder.clients.GitinderAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class GitHubClientTest {

    @Test
    public void loginPostGotAllParamsTest(){
        User testUser = new User("Ferdinand", "fakink123");

        GitinderAPI client = new BackendMockAPI();
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

        GitinderAPI client = new BackendMockAPI();
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
    public void logoutDeleteHeaderIsCorrectTest(){
        GitinderAPI client = new BackendMockAPI();
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
    public void logoutDeleteHeaderIsEmptyTest(){
        final APIResponse apiResponse = new APIResponse();

        GitinderAPI client = new BackendMockAPI();
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
    public void logoutDeleteHeaderIsNullTest(){
        final APIResponse apiResponse = new APIResponse();

        GitinderAPI client = new BackendMockAPI();
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
    public void settingsGetHeaderIsCorrectTest(){
        GitinderAPI client = new BackendMockAPI();
        Call<Settings> call = client.getSettings("abc123");
        Settings profileService = new Settings();
        final Settings testJerry = profileService.createSettings();

        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                assertEquals(200, response.code());
                assertEquals(testJerry.getPreferred_languages(), response.body().getPreferred_languages());
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {

            }
        });
    }

    @Test
    public void settingsGetHeaderIsIncorrectTest(){
        GitinderAPI client = new BackendMockAPI();
        Call<Settings> call = client.getSettings(null);
        final APIResponse apiResponse = new APIResponse();

        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {

            }
        });
    }

    @Test
    public void settingsPutBodyCorrectTest(){
        Settings settingsService = new Settings();
        final Settings testSettings = settingsService.createSettings();

        GitinderAPI client = new BackendMockAPI();
        Call<APIResponse> call = client.updateSettings("abc123", testSettings);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                assertEquals(200, response.code());
                assertEquals("success", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {}
        });
    }

    @Test
    public void settingsPutBodyIncorrectTest(){

        GitinderAPI client = new BackendMockAPI();
        Call<APIResponse> call = client.updateSettings("abc123", null);
        final APIResponse apiResponse = new APIResponse();

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
            public void onFailure(Call<APIResponse> call, Throwable t) {}
        });
    }
}
