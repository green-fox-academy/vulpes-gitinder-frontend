package com.greenfox.gitinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.greenfox.gitinder.activity.Login;

public class MainActivity extends AppCompatActivity {

    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
