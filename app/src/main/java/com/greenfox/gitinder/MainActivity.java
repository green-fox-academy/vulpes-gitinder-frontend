package com.greenfox.gitinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.greenfox.gitinder.Model.TestClass;
import com.greenfox.gitinder.activities.Login;
import com.greenfox.gitinder.dependencyInjection.AppComponent;
import com.greenfox.gitinder.dependencyInjection.DaggerAppComponent;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {


    private TestClass testClass;
    TextView hello;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.text);
        AppComponent appComponent = DaggerAppComponent.builder().build();
        testClass = appComponent.getTestClass();
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
