package com.greenfox.gitinder;

import com.greenfox.gitinder.api.mock.BackendMockAPI;
import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.api.model.GitinderResponse;
import com.greenfox.gitinder.api.model.LoginResponse;
import com.greenfox.gitinder.api.model.SwipeResponse;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.model.Matches;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.Settings;
import com.greenfox.gitinder.model.User;
import com.greenfox.gitinder.model.factory.ErrorMessageFactory;
import com.greenfox.gitinder.model.factory.ProfileFactory;
import com.greenfox.gitinder.model.factory.SettingsFactory;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class GitHubClientTest {

    GitinderAPI client;
    List<String> usernameList;

    @Before
    public void setUp() {
        client = new BackendMockAPI();
        usernameList = ProfileFactory.getAllUsernames();
    }

    @Test
    public void loginPostGotAllParamsTest(){
        User testUser = new User("Ferdinand", "fakink123");

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

    @Test
    public void profilePutBodyCorrectTest(){
        Call<Profile> call = client.getProfile("abs123");
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                assertEquals(200, response.code());

                assertThat(response.body().getUsername(), anyOf(equalTo(usernameList.get(0)),
                        equalTo(usernameList.get(1)), equalTo(usernameList.get(2)), equalTo(usernameList.get(3)),
                        equalTo(usernameList.get(3)), equalTo(usernameList.get(4)), equalTo(usernameList.get(5)),
                        equalTo(usernameList.get(6)), equalTo(usernameList.get(7)), equalTo(usernameList.get(8)),
                        equalTo(usernameList.get(9)), equalTo(usernameList.get(10)), equalTo(usernameList.get(11)),
                        equalTo(usernameList.get(12)), equalTo(usernameList.get(13)), equalTo(usernameList.get(14)),
                        equalTo(usernameList.get(15)), equalTo(usernameList.get(16))));

            }
            @Override
            public void onFailure(Call<Profile> call, Throwable t) {}
        });
    }

    @Test
    public void profileHeaderIsIncorrectTest(){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();
        Call<Profile> call = client.getProfile(null);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
                            response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Profile> call, Throwable t) {}
        });
    }

    @Test
    public void availableCorrectTest(){
        Call<AvailableProfiles> call = client.getAvailable("abc123");
        call.enqueue(new Callback<AvailableProfiles>() {
            @Override
            public void onResponse(Call<AvailableProfiles> call, Response<AvailableProfiles> response) {
                assertEquals(200, response.code());
                assertEquals(10, response.body().getCount().intValue());
                assertThat(response.body().getProfiles().get(0).getUsername(), anyOf(equalTo(usernameList.get(0)),
                        equalTo(usernameList.get(1)), equalTo(usernameList.get(2)), equalTo(usernameList.get(3)),
                        equalTo(usernameList.get(3)), equalTo(usernameList.get(4)), equalTo(usernameList.get(5)),
                        equalTo(usernameList.get(6)), equalTo(usernameList.get(7)), equalTo(usernameList.get(8)),
                        equalTo(usernameList.get(9)), equalTo(usernameList.get(10)), equalTo(usernameList.get(11)),
                        equalTo(usernameList.get(12)), equalTo(usernameList.get(13)), equalTo(usernameList.get(14)),
                        equalTo(usernameList.get(15)), equalTo(usernameList.get(16))));
            }
            @Override
            public void onFailure(Call<AvailableProfiles> call, Throwable t) {}
        });
    }

    @Test
    public void availableHeaderIsIncorrectTest(){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();
        Call<AvailableProfiles> call = client.getAvailable("");
        call.enqueue(new Callback<AvailableProfiles>() {
            @Override
            public void onResponse(Call<AvailableProfiles> call, Response<AvailableProfiles> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
                            response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<AvailableProfiles> call, Throwable t) {}
        });
    }
    @Test
    public void swipeCorrectTest(){
        Call<SwipeResponse> call = client.swipe("abc123", "Splichus", "left");
        call.enqueue(new Callback<SwipeResponse>() {
            @Override
            public void onResponse(Call<SwipeResponse> call, Response<SwipeResponse> response) {
                assertEquals(200, response.code());
                assertEquals("ok", response.body().getStatus());
                assertEquals("success", response.body().getMessage());
            }
            @Override
            public void onFailure(Call<SwipeResponse> call, Throwable t) {}
        });
    }

    @Test
    public void swipeHeaderIsIncorrectTest(){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();
        Call<SwipeResponse> call = client.swipe(null, "splichus", "left");
        call.enqueue(new Callback<SwipeResponse>() {
            @Override
            public void onResponse(Call<SwipeResponse> call, Response<SwipeResponse> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
                            response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<SwipeResponse> call, Throwable t) {}
        });
    }
    @Test
    public void matchesCorrectTest(){
        Call<Matches> call = client.matches("abc123");
        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                assertEquals(200, response.code());
                assertEquals(6, response.body().getMatches().size());
            }
            @Override
            public void onFailure(Call<Matches> call, Throwable t) {}
        });
    }

    @Test
    public void matchesHeaderIsIncorrectTest(){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();
        Call<Matches> call = client.matches("");
        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                assertEquals(403, response.code());
                try {
                    assertEquals(errorMessageFactory.getErrorJSON("Unauthorized request!"),
                            response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Matches> call, Throwable t) {}
        });
    }


}
