package com.greenfox.gitinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.greenfox.gitinder.activities.Login;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.text);
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
