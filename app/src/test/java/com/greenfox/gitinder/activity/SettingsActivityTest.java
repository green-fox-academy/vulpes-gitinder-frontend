package com.greenfox.gitinder.activity;

import android.content.SharedPreferences;

import com.greenfox.gitinder.factory.SharedPreferencesFactory;
import com.greenfox.gitinder.model.UserSettings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class SettingsActivityTest {

    SharedPreferences pref;
    SettingsActivity settings;


    @Test
    public void testIfNotificationsSettingsIsSaving() {
        settings = Robolectric.buildActivity(SettingsActivity.class).create().get();
        pref = SharedPreferencesFactory.getSharedPref();
        assertEquals(false, pref.getBoolean("enableNotifications", false));
        settings.notificationSwitch.performClick();
        assertEquals(true, pref.getBoolean("enableNotifications", false));
        settings.notificationSwitch.performClick();
        assertEquals(false, pref.getBoolean("enableNotifications", false));
    }

    @Test
    public void testIfBckSyncSettingsIsSaving() {
        settings = Robolectric.buildActivity(SettingsActivity.class).create().get();
        pref = SharedPreferencesFactory.getSharedPref();
        assertEquals(false, pref.getBoolean("enableBackgroundSync", false));
        settings.bSyncSwitch.performClick();
        assertEquals(true, pref.getBoolean("enableBackgroundSync", false));
        settings.bSyncSwitch.performClick();
        assertEquals(false, pref.getBoolean("enableBackgroundSync", false));
    }


    @Test
    public void testUserNotifications() {
        settings = Robolectric.buildActivity(SettingsActivity.class).create().get();
        assertEquals(false, settings.userSettings.isEnableNotification());
        settings.notificationSwitch.performClick();
        assertEquals(true, settings.userSettings.isEnableNotification());
        settings.notificationSwitch.performClick();
        assertEquals(false, settings.userSettings.isEnableNotification());

    }
}
