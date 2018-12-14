package com.greenfox.gitinder;

import com.greenfox.gitinder.Model.APIResponse;
import com.greenfox.gitinder.Model.User;
import com.greenfox.gitinder.clients.GitHubClient;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockService implements GitHubClient {

    @Override
    public Call<APIResponse> usernameAndToken(final User user) {
        return new Call<APIResponse>() {

            @Override
            public Response<APIResponse> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<APIResponse> callback) {
                APIResponse apiResponse = new APIResponse();
                if(user.getUsername() == null) {
                    callback.onResponse(this, Response.<APIResponse>error(400,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    apiResponse.getErrorJSON("Username is missing!"))));
                } else if (user.getAccess_token() == null) {
                    callback.onResponse(this, Response.<APIResponse>error(400,
                            ResponseBody.create(MediaType.parse("application/json"),
                                                apiResponse.getErrorJSON("Access token is missing!"))));
                } else {
                    apiResponse.setStatus("ok");
                    apiResponse.setGitinder_token("abc123");
                    callback.onResponse(this, Response.success(apiResponse));
                }
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<APIResponse> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }

    @Override
    public Call<APIResponse> logoutUser(final String header) {
        return new Call<APIResponse>() {
            @Override
            public Response<APIResponse> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<APIResponse> callback) {
                APIResponse apiResponse = new APIResponse();
                if(header == null || header.isEmpty()) {
                    callback.onResponse(this, Response.<APIResponse>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    apiResponse.getErrorJSON("Unauthorized request!"))));
                } else {
                    apiResponse.setStatus("ok");
                    apiResponse.setMessage("Logged out successfully!");
                    callback.onResponse(this, Response.success(apiResponse));
                }
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<APIResponse> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
}
