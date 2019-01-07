package com.greenfox.gitinder.activity.mainActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activity.Login;
import com.greenfox.gitinder.factory.SharedPreferencesFactory;
import com.greenfox.gitinder.mainActivityFragment.MatchesFragment;
import com.greenfox.gitinder.mainActivityFragment.SettingsFragment;
import com.greenfox.gitinder.mainActivityFragment.SwipingFragment;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.shadow.ShadowFragmentPagerAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowPreference;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.robolectric.util.FragmentTestUtil.startFragment;

import javax.inject.Inject;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    MainActivity mainActivity;

    @Test
    public void tokenIsPresentRedirectionTest() {
        SharedPreferences preferences = SharedPreferencesFactory.getSharedPref();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.GITINDER_TOKEN, "abc123").apply();

        mainActivity = Robolectric.setupActivity(MainActivity.class);
        ViewPager viewPager = (ViewPager)mainActivity.findViewById(R.id.container);

        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(null, actualIntent);
    }

    @Test
    public void tokenIsNotPresentRedirectionTest() {
        SharedPreferences preferencesTest = ApplicationProvider.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTest = preferencesTest.edit();
        editorTest.clear();

        mainActivity = Robolectric.setupActivity(MainActivity.class);

        Intent expectedIntent = new Intent(mainActivity, Login.class);
        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}
