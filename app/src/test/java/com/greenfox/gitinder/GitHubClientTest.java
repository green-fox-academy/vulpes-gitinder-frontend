package com.greenfox.gitinder;

import com.greenfox.gitinder.API.BackendMockAPI;
import com.greenfox.gitinder.Model.Response.GitinderResponse;
import com.greenfox.gitinder.Model.Response.LoginResponse;
import com.greenfox.gitinder.Model.Response.SettingsResponse;
import com.greenfox.gitinder.Model.Settings;
import com.greenfox.gitinder.Model.User;
import com.greenfox.gitinder.API.GitinderAPI;

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
        Call<LoginResponse> call = client.login(testUser);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                assertEquals(200, response.code());
                assertEquals("abc123", response.body().getGitinderToken());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {}
        });
    }

    @Test
    public void loginPostMissingParamTest(){
        User testUser = new User("Ferdinand");
        final LoginResponse apiResponse = new LoginResponse();

        GitinderAPI client = new BackendMockAPI();
        Call<LoginResponse> call = client.login(testUser);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                assertEquals(400, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Access token is missing!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {}
        });
    }

    @Test
    public void logoutDeleteHeaderIsCorrectTest(){
        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.logoutUser("abc123");

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(200, response.code());
                assertEquals("Logged out successfully!",response.body().getMessage());
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void logoutDeleteHeaderIsEmptyTest(){
        final GitinderResponse apiResponse = new GitinderResponse();

        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.logoutUser("");

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void logoutDeleteHeaderIsNullTest(){
        final GitinderResponse apiResponse = new GitinderResponse();

        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.logoutUser(null);

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                            response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void settingsGetHeaderIsCorrectTest(){
        GitinderAPI client = new BackendMockAPI();
        Call<SettingsResponse> call = client.getSettings("abc123");
        Settings profileService = new Settings();
        final Settings testJerry = profileService.createSettings();

        call.enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                assertEquals(200, response.code());
                assertEquals(testJerry.getPreferredLanguages(), response.body().getSettings().getPreferredLanguages());
            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void settingsGetHeaderIsIncorrectTest(){
        GitinderAPI client = new BackendMockAPI();
        Call<SettingsResponse> call = client.getSettings(null);
        final GitinderResponse apiResponse = new GitinderResponse();

        call.enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void settingsPutBodyCorrectTest(){
        Settings settingsService = new Settings();
        final Settings testSettings = settingsService.createSettings();

        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.updateSettings("abc123", testSettings);

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(200, response.code());
                assertEquals("success", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {}
        });
    }

    @Test
    public void settingsPutBodyIncorrectTest(){

        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.updateSettings("abc123", null);
        final GitinderResponse apiResponse = new GitinderResponse();

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON("Unauthorized request!"),
                                 response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GitinderResponse> call, Throwable t) {}
        });
    }
}
