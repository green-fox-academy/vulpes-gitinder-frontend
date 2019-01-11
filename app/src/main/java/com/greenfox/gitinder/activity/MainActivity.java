package com.greenfox.gitinder.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button settingsButton;
    Button profileButton;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsButton = findViewById(R.id.main_settings_button);
        profileButton = findViewById(R.id.main_profile_button);
        if (!BuildConfig.FLAVOR.equals("dev")) {
            profileButton.setVisibility(View.GONE);
        }


        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)){
            Log.d(TAG, "Token is missing!");
            toLogin();
        } else {
            Log.d(TAG, "Token is present.");
        }
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfile(v);
            }
        });

    }

    public void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
    public void toProfile(View view) {
        Intent intent = new Intent(this, ProfileDetails.class);
        startActivity(intent);
    }
}
