package com.greenfox.gitinder.api;

import com.greenfox.gitinder.Model.Factory.ErrorMessageFactory;
import com.greenfox.gitinder.Model.Factory.SettingsFactory;
import com.greenfox.gitinder.Model.Response.GitinderResponse;
import com.greenfox.gitinder.Model.Response.LoginResponse;
import com.greenfox.gitinder.Model.Settings;
import com.greenfox.gitinder.Model.User;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class BackendMockAPI implements GitinderAPI {x

    @Override
    public CallMock<LoginResponse> login(final User user){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        return new CallMock<LoginResponse>(){

            @Override
            public void enqueue(Callback<LoginResponse> callback) {
                LoginResponse loginResponse = new LoginResponse();
                if (user.getUsername() == null) {
                    callback.onResponse(this, Response.<LoginResponse>error(400,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Username is missing!"))));
                } else if (user.getAccessToken() == null) {
                    callback.onResponse(this, Response.<LoginResponse>error(400,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Access token is missing!"))));
                } else {
                    loginResponse.setStatus("ok");
                    loginResponse.setGitinderToken("abc123");
                    callback.onResponse(this, Response.success(loginResponse));
                }
            }
        };
    }

    @Override
    public CallMock<GitinderResponse> logoutUser(final String header){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        return new CallMock<GitinderResponse>(){

            @Override
            public void enqueue(Callback<GitinderResponse> callback) {
                GitinderResponse gitinderResponse = new GitinderResponse();
                if(header == null || header.isEmpty()) {
                    callback.onResponse(this, Response.<GitinderResponse>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    gitinderResponse.setStatus("ok");
                    gitinderResponse.setMessage("Logged out successfully!");
                    callback.onResponse(this, Response.success(gitinderResponse));
                }
            }
        };
    }

    @Override
    public CallMock<Settings> getSettings(final String header){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        return new CallMock<Settings>(){

            @Override
            public void enqueue(Callback<Settings> callback) {
                SettingsFactory settingsFactory = new SettingsFactory();

                Settings settings = settingsFactory.createSettings();

                if(header == null || header.isEmpty()) {
                    callback.onResponse(this, Response.<Settings>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(settings));
                }
            }
        };
    }

    @Override
    public CallMock<GitinderResponse> updateSettings(final String header, final Settings settings){
        final ErrorMessageFactory errorMessageFactory = new ErrorMessageFactory();

        return new CallMock<GitinderResponse>(){

            @Override
            public void enqueue(Callback<GitinderResponse> callback) {
                GitinderResponse apiResponse = new GitinderResponse();
                apiResponse.setStatus("ok");
                apiResponse.setMessage("success");

                if(header == null || header.isEmpty() || settings == null) {
                    callback.onResponse(this, Response.<GitinderResponse>error(403,
                            ResponseBody.create(MediaType.parse("application/json"),
                                    errorMessageFactory.getErrorJSON("Unauthorized request!"))));
                } else {
                    callback.onResponse(this, Response.success(apiResponse));
                }
            }
        };
    }
}
