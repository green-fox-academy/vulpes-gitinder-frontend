package com.greenfox.gitinder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.service.GithubAPI;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    public Button login;

    @Inject
    SharedPreferences sharedPreferences;

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
            saveGitHubToken(uri, githubAPI);
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
    // Calls the githubAPI and saves the returned github token
    public void saveGitHubToken(Uri uri, GithubAPI githubAPI) {
        String code = uri.getQueryParameter("code");
        Call<GitHubToken> call = githubAPI.getToken(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_CLIENT_SECRET, code);

        call.enqueue(new Callback<GitHubToken>() {

            @Override
            public void onResponse(Call<GitHubToken> call, Response<GitHubToken> response) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.GITINDER_TOKEN, response.body().getToken()).apply();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<GitHubToken> call, Throwable t) {
                Toast.makeText(Login.this, "Failed to call", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
