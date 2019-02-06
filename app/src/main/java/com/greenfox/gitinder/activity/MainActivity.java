package com.greenfox.gitinder.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.greenfox.gitinder.BuildConfig;
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

public class MainActivity extends AppCompatActivity implements MatchService.NewMatchCountListener {
    private static final String TAG = "MainActivity";
    private NonSwipeableViewPager mViewPager;
    private Toolbar toolbar;
    private SectionsPageAdapter sectionsPageAdapter;
    private Animation animation;

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
        toolbar = findViewById(R.id.main_toolbar);

        floatingActionButtonText = findViewById(R.id.floating_action_button_text);
        floatingActionButton = findViewById(R.id.floating_action_button);

        matchService.setNewMatchCountListener(this);
        hideFloatingButtonWhenNoNewMatches();
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.gitinder_icon);
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

    public void setupViewPager(NonSwipeableViewPager viewPager) {
        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.toolbar_debug_settings) {
            Intent intent = new Intent(this, SettingsTestingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        MenuItem build = menu.findItem(R.id.toolbar_debug_settings);
        if (!BuildConfig.FLAVOR.equals("dev")) {
            build.setVisible(false);
        } else {
            build.setVisible(true);
        }
        ImageView rotateButton = (ImageView) menu.findItem(R.id.toolbar_refresh).getActionView();
        if (rotateButton != null) {
            rotateButton.setImageResource(R.drawable.button_refresh);
            rotateButton.setOnClickListener(v -> {
                v.startAnimation(animation);
                    sectionsPageAdapter.getItem(mViewPager.getCurrentItem()).reload();
                });
        }
        return true;
    }

    public void toMatchesFragment(){
        mViewPager.setCurrentItem(1);
    }


    @Override
    public void onMatchCountChanged(int newMatchCount) {
        Log.d(TAG, "onMatchCountChanged: " + newMatchCount);
        floatingActionButtonText.setText(String.valueOf(newMatchCount));
        hideFloatingButtonWhenNoNewMatches();
    }

    public void hideFloatingButtonWhenNoNewMatches(){
        if(matchService.getNewMatchesCount() < 1){
            floatingActionButton.hide();
            floatingActionButtonText.setText("");
        } else {
            floatingActionButton.show();
            floatingActionButtonText.setText(String.valueOf(matchService.getNewMatchesCount()));
        }
    }
}
