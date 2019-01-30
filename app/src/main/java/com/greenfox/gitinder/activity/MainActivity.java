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
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.fragment.main.MatchesFragment;
import com.greenfox.gitinder.fragment.main.SettingsFragment;
import com.greenfox.gitinder.fragment.main.SwipingFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.NonSwipeableViewPager;
import com.greenfox.gitinder.model.factory.MatchFactory;
import com.greenfox.gitinder.service.NotificationService;

import javax.inject.Inject;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NonSwipeableViewPager mViewPager;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    NotificationService notificationService;

    @Inject
    MatchService matchService;

    FloatingActionButton floatingActionButton;
    public TextView floatingActionButtonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButtonText = findViewById(R.id.floating_action_button_text);
        floatingActionButton = findViewById(R.id.floating_action_button);

        matchService = new MatchService(sharedPreferences);
        setupSharedPreferencesListener();
        matchService.removeNewMatchesCount();

//        sharedPreferences.edit().remove(Constants.MATCHES_COUNT).apply();

        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)) {
            Log.d(TAG, "Token is missing!");
            toLogin();
        } else {
            Log.d(TAG, "Token is present.");
        }

        Match match = MatchFactory.createNewMatch();
        notificationService.createNotificationChannel(this);
        notificationService.pushNewMatchNotification(match, this);

        Log.d(TAG, "onCreate: Starting.");

        Log.d(TAG, "MATCHES_COUNT: " + matchService.getNewMatchesCount());
        Log.d(TAG, "buttonText: " + floatingActionButtonText.getText());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        floatingActionButton.setOnClickListener(v -> {
            toMatchesFragment();
        });

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.gitinder_icon);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Constants.GO_TO_MATCHES)) {
            toMatchesFragment();
        }

    }

    public void setupViewPager (NonSwipeableViewPager viewPager){
        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        sectionsPageAdapter.addFragment(new SwipingFragment(), getString(R.string.tab_title_swiping));
        sectionsPageAdapter.addFragment(new MatchesFragment(), getString(R.string.tab_title_matches));
        sectionsPageAdapter.addFragment(new SettingsFragment(), getString(R.string.tab_title_settings));
        viewPager.setAdapter(sectionsPageAdapter);
    }

    public void toLogin () {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    public void toMatchesFragment(){
        mViewPager.setCurrentItem(1);
    }

    public void setupSharedPreferencesListener(){
        sharedPreferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
            if (key.equals(Constants.MATCHES_COUNT)){
                floatingActionButtonText.setText(matchService.getNewMatchesCount());

                matchService.hideFloatingButtonWhenNoNewMatches(floatingActionButton, floatingActionButtonText);
            }
        });
    }

}
