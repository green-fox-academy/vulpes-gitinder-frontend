package com.greenfox.gitinder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.SectionsPageAdapter;
import com.greenfox.gitinder.fragment.main.MatchesFragment;
import com.greenfox.gitinder.fragment.main.SettingsFragment;
import com.greenfox.gitinder.fragment.main.SwipingFragment;
import com.greenfox.gitinder.model.NonSwipeableViewPager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NonSwipeableViewPager mViewPager;

    @Inject
    SharedPreferences sharedPreferences;

    FloatingActionButton floatingActionButton;
    TextView floatingActionButtonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)){
            Log.d(TAG, "Token is missing!");
            toLogin();
        } else {
            Log.d(TAG, "Token is present.");
        }

        Log.d(TAG, "onCreate: Starting.");

        floatingActionButtonText = findViewById(R.id.floating_action_button_text);
        floatingActionButton = findViewById(R.id.floating_action_button);

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        floatingActionButton.setOnClickListener(v -> {
            mViewPager.setCurrentItem(1);
        });

        if(sharedPreferences.getString(Constants.MATCHES_COUNT, "").equals("0")){
            floatingActionButton.hide();
            floatingActionButtonText.setText("");
        } else {
            floatingActionButton.show();
            floatingActionButtonText.setText(sharedPreferences.getString(Constants.MATCHES_COUNT, ""));
        }


        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.gitinder_icon);
    }

    public void setupViewPager(NonSwipeableViewPager viewPager){
        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        sectionsPageAdapter.addFragment(new SwipingFragment(), getString(R.string.tab_title_swiping));
        sectionsPageAdapter.addFragment(new MatchesFragment(), getString(R.string.tab_title_matches));
        sectionsPageAdapter.addFragment(new SettingsFragment(), getString(R.string.tab_title_settings));
        viewPager.setAdapter(sectionsPageAdapter);
    }

    public void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.edit().putString(Constants.MATCHES_COUNT, "").apply();
    }
}
