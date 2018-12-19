package com.greenfox.gitinder.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.greenfox.gitinder.api.service.GitHubClient;
import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    SharedPreferences spref;
    public Button login;
    GitHubClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spref = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        //checks the Shared preference for existing gitinder token
        login = findViewById(R.id.btn_login_with_github);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        client = retrofit.create(GitHubClient.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String intentString = getIntent().toString();
        Uri uri = getIntent().getData();
        if (uri != null) {
            saveGitHubToken(uri, client);
        }
        if (spref.contains(Constants.GITINDER_TOKEN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (uri == null){
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.show(getSupportFragmentManager(), "loginDialog");
        }

    }

    // After clicking the Github Oauth is started
    public void loginWithGithub(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id="
                +Constants.GITHUB_CLIENT_ID
                +"&redirect_uri="+Constants.GITHUB_CALLBACK));
        startActivity(intent);
    }
    // Calls the githubAPI and saves the returned github token
    public void saveGitHubToken(Uri uri, GitHubClient client) {
        String code = uri.getQueryParameter("code");
        Call<GitHubToken> call = client.getToken(Constants.GITHUB_CLIENT_ID, Constants.GITHUB_CLIENT_SECRET, code);

        call.enqueue(new Callback<GitHubToken>() {

            @Override
            public void onResponse(Call<GitHubToken> call, Response<GitHubToken> response) {
                SharedPreferences.Editor editor = spref.edit();
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

