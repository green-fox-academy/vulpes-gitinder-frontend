package com.greenfox.gitinder.activityREF;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activity.Login;
import com.greenfox.gitinder.adapter.SectionsPageAdapter;
import com.greenfox.gitinder.fragment.main.SwipingFragment;
import com.greenfox.gitinder.fragment.main.MatchesFragment;
import com.greenfox.gitinder.fragment.main.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        if (!sharedPreferences.contains(Constants.GITINDER_TOKEN)){
            Log.d("token_checking", "Token is missing!");
            toLogin();
        } else {
            Log.d("token_checking", "Token is present.");
        }

        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.gitinder_icon);
    }

    public void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        sectionsPageAdapter.addFragment(new SwipingFragment(),"Swiping");
        sectionsPageAdapter.addFragment(new MatchesFragment(),"Matches");
        sectionsPageAdapter.addFragment(new SettingsFragment(),"Settings");
        viewPager.setAdapter(sectionsPageAdapter);
    }

    public void toLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
