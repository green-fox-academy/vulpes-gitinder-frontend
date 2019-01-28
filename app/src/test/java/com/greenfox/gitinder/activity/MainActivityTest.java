package com.greenfox.gitinder.activity;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.adapter.ShadowCardStackLayoutManager;
import com.greenfox.gitinder.adapter.ShadowViewPager;
import com.greenfox.gitinder.factory.SharedPreferencesFactory;
import com.greenfox.gitinder.fragment.main.MatchesFragment;
import com.squareup.picasso.MockPicasso;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowViewPager.class, ShadowCardStackLayoutManager.class})
public class MainActivityTest {

    MainActivity mainActivity;
    SharedPreferences sharedPreferences;

    @Before
    public void setUp(){
        sharedPreferences = SharedPreferencesFactory.getSharedPref();
        MockPicasso.init();
    }

    @Test
    public void floatingButtonShowingCorrectNumberOfNewMatches(){
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        assertEquals(sharedPreferences.getString(Constants.MATCHES_COUNT, ""), mainActivity.floatingActionButtonText.getText());
    }

    @Test
    public void tokenIsPresentRedirectionTest() {
        sharedPreferences.edit().putString(Constants.GITINDER_TOKEN, "abc123").apply();
        mainActivity = Robolectric.setupActivity(MainActivity.class);

        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(null, actualIntent);
    }

    @Test
    public void tokenIsNotPresentRedirectionTest() {
        sharedPreferences.edit().clear().apply();
        mainActivity = Robolectric.setupActivity(MainActivity.class);

        Intent expectedIntent = new Intent(mainActivity, Login.class);
        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}
