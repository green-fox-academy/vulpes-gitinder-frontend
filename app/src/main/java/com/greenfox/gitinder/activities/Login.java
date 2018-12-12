package com.greenfox.gitinder.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.R;

import javax.inject.Inject;

public class Login extends AppCompatActivity {

    Button login;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("preference", Context.MODE_PRIVATE);
        //checks the SHared preference for existing gitinder token
        if (preferences.contains("gitinder-token")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.loginwgh);
    }
    // After clicking the Github Oauth is started
    public void loginWithGithub(View view) {
        Intent intent = new Intent(this, LoginGitHub.class);
        startActivity(intent);
    }
}
