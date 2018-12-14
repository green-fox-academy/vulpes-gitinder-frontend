package com.greenfox.gitinder.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.Toast;

import com.greenfox.gitinder.Clients.GitHubClient;
import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.Model.Constants;
import com.greenfox.gitinder.Model.GitHubToken;
import com.greenfox.gitinder.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    Button login;
    SharedPreferences preferences;
    private final String TAG = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        //checks the Shared preference for existing gitinder token
        if (preferences.contains(Constants.GITINDER_TOKEN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login_with_github);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String intentString = getIntent().toString();
        Uri uri = getIntent().getData();
        if (uri != null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://github.com/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            GitHubClient client = retrofit.create(GitHubClient.class);
            saveGitHubToken(uri, client);
        }
    }

    // After clicking the Github Oauth is started
    public void loginWithGithub(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id="
                +Constants.GITHUB_CLIENT_ID
                +"&redirect_uri="+Constants.GITHUB_CALLBACK));
        startActivity(intent);
    }
    public void saveGitHubToken(Uri uri, GitHubClient client) {
        String code = uri.getQueryParameter("code");

        Call<GitHubToken> call = client.getToken(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_CLIENT_SECRET, code);
        preferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        call.enqueue(new Callback<GitHubToken>() {
            @Override
            public void onResponse(Call<GitHubToken> call, Response<GitHubToken> response) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.GITINDER_TOKEN, response.body().getToken()).apply();
            }

            @Override
            public void onFailure(Call<GitHubToken> call, Throwable t) {
                Toast.makeText(Login.this, "Failed to call", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

