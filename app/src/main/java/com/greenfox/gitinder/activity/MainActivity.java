package com.greenfox.gitinder.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    Button settingsButton;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsButton = findViewById(R.id.main_settings_button);

        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)){
            Log.d("token_checking", "Token is missing!");
            toLogin();
        } else {
            Log.d("token_checking", "Token is present.");
        }
    }

    public void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
