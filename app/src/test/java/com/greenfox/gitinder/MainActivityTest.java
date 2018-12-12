package com.greenfox.gitinder;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.greenfox.gitinder.Model.Constants;
import com.greenfox.gitinder.activities.Login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Test
    public void helloButtonTest() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        assertEquals("Settings", mainActivity.settingsButton.getText());
    }

    @Test
    public void tokenIsPresentRedirectionTest() {
        SharedPreferences preferences = ApplicationProvider.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.GITINDER_TOKEN, "abc123").apply();

        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.sharedPreferences.edit().putString(Constants.GITINDER_TOKEN, "abc123").apply();

        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(null, actualIntent);
    }

    @Test
    public void tokenIsNotPresentRedirectionTest() {
        SharedPreferences preferencesTest = ApplicationProvider.getApplicationContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorTest = preferencesTest.edit();
        editorTest.clear();

        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.sharedPreferences.edit().clear().apply();

        Intent expectedIntent = new Intent(mainActivity, Login.class);
        Intent actualIntent = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }
}