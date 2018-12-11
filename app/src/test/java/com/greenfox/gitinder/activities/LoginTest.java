package com.greenfox.gitinder.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.greenfox.gitinder.MainActivity;

import org.apache.tools.ant.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadow.api.Shadow;

import androidx.test.core.app.ApplicationProvider;

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
    public void testRedirectFalse() {
        login = Robolectric.setupActivity(Login.class);
        SharedPreferences preferences = login.preferences;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("gitinder-token", "aaa");
        Intent expectedIntent = new Intent(login, MainActivity.class);
        Intent actual = shadowOf().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}