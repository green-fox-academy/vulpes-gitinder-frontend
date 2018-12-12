package com.greenfox.gitinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.gitinder.Model.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activities.Login;

import org.w3c.dom.Text;

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
}
