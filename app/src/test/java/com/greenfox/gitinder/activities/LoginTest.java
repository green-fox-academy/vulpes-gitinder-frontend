package com.greenfox.gitinder.activities;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.greenfox.gitinder.MainActivity;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowApplication;

import androidx.test.core.app.ApplicationProvider;
import retrofit2.http.PUT;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.*;

@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    Login login;

    @Test
    public void buttonTextTest() {
        login = Robolectric.setupActivity(Login.class);
        assertEquals("Login with Github", login.login.getText());
    }
    @Test
    public void testRedirectTrue() {
        SharedPreferences preferences =  ApplicationProvider.getApplicationContext().getSharedPreferences("preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("gitinder-token", "aaa").apply();
        login = Robolectric.setupActivity(Login.class);
        Intent expectedIntent = new Intent(login, MainActivity.class);
        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
    @Test
    public void testRedirectFail() {
        SharedPreferences preferences = ApplicationProvider.getApplicationContext().getSharedPreferences("preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ryba", "aaa");
        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
        assertEquals(null, actual);
    }
    @Test
    public void testButtonWillRedirect() {
        SharedPreferences preferences =  ApplicationProvider.getApplicationContext().getSharedPreferences("preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("gitinder-token", "aaa").apply();
        login = Robolectric.setupActivity(Login.class);
        login.login.performClick();
        Intent expectedIntent = new Intent(login, LoginGitHub.class);
        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}