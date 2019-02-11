package com.greenfox.gitinder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.api.model.CustomCallback;
import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.model.GitHubUsername;
import com.greenfox.gitinder.api.model.LoginResponse;
import com.greenfox.gitinder.api.service.GithubAPI;
import com.greenfox.gitinder.api.service.GithubTokenAPI;
import com.greenfox.gitinder.api.service.GitinderAPIService;
import com.greenfox.gitinder.model.User;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    GithubTokenAPI githubTokenAPI;

    @Inject
    GitinderAPIService gitinderAPI;

    @Inject
    GithubAPI githubAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        //checks the Shared preference for existing gitinder token
        if (sharedPreferences.contains(Constants.GITINDER_TOKEN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        if (uri != null) {
            String code = uri.getQueryParameter("code");
            saveGitHubToken(code);
        }
        if (sharedPreferences.contains(Constants.GITINDER_TOKEN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (uri == null) {
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.setCancelable(false);
            loginDialog.show(getSupportFragmentManager(), "loginDialog");
        }

    }

    public void saveGitHubToken(String code) {

        githubTokenAPI.getToken(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_CLIENT_SECRET, code).enqueue(new CustomCallback<GitHubToken>() {
            @Override
            public void onResponse(Call<GitHubToken> call, Response<GitHubToken> response) {
                githubAPI.getGitHubUsername("token " + response.body().getToken()).enqueue(new CustomCallback<GitHubUsername>() {
                    @Override
                    public void onResponse(Call<GitHubUsername> call, Response<GitHubUsername> response2) {
                        gitinderAPI.provide(Constants.LOGIN).login(new User(response2.body().getLogin(), response.body().getToken())).enqueue(new CustomCallback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response3) {
                                sharedPreferences.edit().putString(Constants.USER_PICTURE, response2.body().getAvatarUrl()).apply();
                                sharedPreferences.edit().putString(Constants.USERNAME, response2.body().getLogin()).apply();
                                sharedPreferences.edit().putString(Constants.GITINDER_TOKEN, response3.body().getGitinderToken()).apply();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
            }
        });
    }
}
