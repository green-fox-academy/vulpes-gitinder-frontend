package com.greenfox.gitinder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.api.model.CustomCallback;
import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.model.GitHubUsername;
import com.greenfox.gitinder.api.model.LoginResponse;
import com.greenfox.gitinder.api.service.GithubAPI;
import com.greenfox.gitinder.api.service.GithubTokenAPI;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.model.User;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";


    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    GithubTokenAPI githubTokenAPI;

    @Inject
    GitinderAPI gitinderAPI;

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
            saveGitHubToken(uri);
        }
        if (sharedPreferences.contains(Constants.GITINDER_TOKEN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (uri == null){
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.show(getSupportFragmentManager(), "loginDialog");
        }

    }

    // After clicking the Github Oauth is started
        public void loginWithGithub(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.GITHUB_URL + Constants.GITHUB_CLIENT_ID
                                                                 + "&redirect_uri=" + Constants.GITHUB_CALLBACK));
        startActivity(intent);
    }
    // Calls the githubTokenAPI and saves the returned github token
    public void saveGitHubToken(Uri uri) {
        String code = uri.getQueryParameter("code");
        Call<GitHubToken> call = githubTokenAPI.getToken(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_CLIENT_SECRET, code);

        call.enqueue(new CustomCallback<GitHubToken>() {

            @Override
            public void onResponse(Call<GitHubToken> call, Response<GitHubToken> response) {
                Call<GitHubUsername> gitHubUsernameCall = githubAPI.getGitHubUsername("token " + response.body().getToken());

                gitHubUsernameCall.enqueue(new CustomCallback<GitHubUsername>() {
                    @Override
                    public void onResponse(Call<GitHubUsername> call, Response<GitHubUsername> response2) {
                        Call<LoginResponse> loginResponseCall = gitinderAPI.login(new User(response2.body().getLogin(), response.body().getToken()));

                        loginResponseCall.enqueue(new CustomCallback<LoginResponse>() {

                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response3) {
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
