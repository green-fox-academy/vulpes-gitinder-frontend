package com.greenfox.gitinder.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.Model.Constants;
import com.greenfox.gitinder.R;

public class Login extends AppCompatActivity {

    Button login;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        //checks the SHared preference for existing gitinder token
        if (preferences.contains(Constants.GITINDER_TOKEN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login_with_github);
    }
    // After clicking the Github Oauth is started
    public void loginWithGithub(View view) {
        Intent intent = new Intent(this, LoginGitHub.class);
        startActivity(intent);
    }
}

