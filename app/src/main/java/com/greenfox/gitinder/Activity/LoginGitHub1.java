package com.greenfox.gitinder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.greenfox.gitinder.R;

public class LoginGitHub1 extends AppCompatActivity {

    String clientID = "cc7cbc02bcfad0130c9d";
    String clientSecret = "7f0a5f98fc25d338d13ce1f5a287bced6975f3e9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_git_hub);
    }
}

