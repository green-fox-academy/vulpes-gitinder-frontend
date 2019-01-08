package com.greenfox.gitinder.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.adapter.ShadowViewPager;
import com.greenfox.gitinder.factory.SharedPreferencesFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowViewPager.class})
public class MainActivityTest {

    MainActivity mainActivity;

    @Test
    public void tokenIsPresentRedirectionTest() {
        SharedPreferences preferences = SharedPreferencesFactory.getSharedPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.GITINDER_TOKEN, "abc123").apply();

        mainActivity = Robolectric.setupActivity(MainActivity.class);

        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(null, actualIntent);
    }

    @Test
    public void tokenIsNotPresentRedirectionTest() {
        SharedPreferences preferences = SharedPreferencesFactory.getSharedPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();

        mainActivity = Robolectric.setupActivity(MainActivity.class);

        Intent expectedIntent = new Intent(mainActivity, Login.class);
        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}
