package com.greenfox.gitinder.api.mock;

import com.greenfox.gitinder.modelBANANA.Factorysad.ErrorMessageFactory;
import com.greenfox.gitinder.modelBANANA.Factorysad.SettingsFactory;
import com.greenfox.gitinder.modelBANANA.Responsesad.GitinderResponse;
import com.greenfox.gitinder.modelBANANA.Responsesad.LoginResponse;
import com.greenfox.gitinder.modelBANANA.Settings;
import com.greenfox.gitinder.modelBANANA.User;
import com.greenfox.gitinder.api.service.GitinderAPI;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class BackendMockAPI implements GitinderAPI {

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
