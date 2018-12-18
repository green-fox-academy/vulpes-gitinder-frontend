package com.greenfox.gitinder.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;


import dagger.android.AndroidInjection;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
    }

    public void toLogin(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }
}
