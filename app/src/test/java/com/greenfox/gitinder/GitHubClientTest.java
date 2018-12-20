package com.greenfox.gitinder;

import com.greenfox.gitinder.apiTest.mock.BackendMockAPI;
import com.greenfox.gitinder.model.Factory.ErrorMessageFactory;
import com.greenfox.gitinder.model.Factory.SettingsFactory;
import com.greenfox.gitinder.model.Response.GitinderResponse;
import com.greenfox.gitinder.model.Response.LoginResponse;
import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.model.User;
import com.greenfox.gitinder.apiTest.service.GitinderAPI;

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
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        User testUser = new User("Ferdinand");
        final LoginResponse apiResponse = new LoginResponse();

        GitinderAPI client = new BackendMockAPI();
        Call<LoginResponse> call = client.login(testUser);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                assertEquals(400, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Access token is missing!"),
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
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.logoutUser("");

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
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
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.logoutUser(null);

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
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
        Call<Settings> call = client.getSettings("abc123");
        SettingsFactory settingsFactory = new SettingsFactory();
        final Settings testJerry = settingsFactory.createSettings();

        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                assertEquals(200, response.code());
                assertEquals(testJerry.getPreferredLanguages(), response.body().getPreferredLanguages());
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {

            }
        });
    }

    @Test
    public void settingsGetHeaderIsIncorrectTest(){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        GitinderAPI client = new BackendMockAPI();
        Call<Settings> call = client.getSettings(null);

        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
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
        SettingsFactory settingsFactory = new SettingsFactory();
        final Settings testSettings = settingsFactory.createSettings();

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
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        GitinderAPI client = new BackendMockAPI();
        Call<GitinderResponse> call = client.updateSettings("abc123", null);

        call.enqueue(new Callback<GitinderResponse>() {
            @Override
            public void onResponse(Call<GitinderResponse> call, Response<GitinderResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
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
