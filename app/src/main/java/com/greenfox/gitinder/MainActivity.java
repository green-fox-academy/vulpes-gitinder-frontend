package com.greenfox.gitinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greenfox.gitinder.Model.Constants;
import com.greenfox.gitinder.activities.Login;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button settingsButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsButton = findViewById(R.id.main_settings_button);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constants.GITINDER_TOKEN, "abc123").apply();

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
