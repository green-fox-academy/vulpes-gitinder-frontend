package com.greenfox.gitinder;

import com.greenfox.gitinder.Model.APIResponse;
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
    public void loginEndpointGotAllParamsTest(){
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
    public void loginEndpointMissingParamTest(){
        User testUser = new User("Ferdinand");
        final APIResponse apiResponse = new APIResponse();

        GitHubClient client = new MockService();
        Call<APIResponse> call = client.usernameAndToken(testUser);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                assertEquals(400, response.code());
                try {
                    assertEquals(apiResponse.getErrorJSON(), response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {}
        });
    }


}
