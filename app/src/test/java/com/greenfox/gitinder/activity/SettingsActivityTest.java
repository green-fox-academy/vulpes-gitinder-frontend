package com.greenfox.gitinder.activity;

import android.content.SharedPreferences;
import android.widget.SeekBar;

import com.greenfox.gitinder.factory.SharedPreferencesFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowSeekBar;


import androidx.test.core.app.ApplicationProvider;

import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class SettingsActivityTest {

    SharedPreferences pref;
    SettingsActivity settingsActivity;
    private SeekBar seekBar;
    private ShadowSeekBar shadow;
    private SeekBar.OnSeekBarChangeListener listener;

    @Before
    public void seekBarSetup(){
        seekBar = new SeekBar(ApplicationProvider.getApplicationContext());
        shadow = Shadows.shadowOf(seekBar);
        listener = new TestSeekBarChangedListener();
        seekBar.setOnSeekBarChangeListener(listener);
    }

    @Test
    public void testIfNotificationsSettingsIsSaving() {
        settingsActivity = Robolectric.buildActivity(SettingsActivity.class).create().get();
        pref = SharedPreferencesFactory.getSharedPref();
        assertEquals(false, pref.getBoolean("enableNotifications", false));
        settingsActivity.notificationSwitch.performClick();
        assertEquals(true, pref.getBoolean("enableNotifications", false));
        settingsActivity.notificationSwitch.performClick();
        assertEquals(false, pref.getBoolean("enableNotifications", false));
    }

    @Test
    public void testIfBckSyncSettingsIsSaving() {
        settingsActivity = Robolectric.buildActivity(SettingsActivity.class).create().get();
        pref = SharedPreferencesFactory.getSharedPref();
        assertEquals(false, pref.getBoolean("enableBackgroundSync", false));
        settingsActivity.bSyncSwitch.performClick();
        assertEquals(true, pref.getBoolean("enableBackgroundSync", false));
        settingsActivity.bSyncSwitch.performClick();
        assertEquals(false, pref.getBoolean("enableBackgroundSync", false));
    }


    @Test
    public void testUserNotifications() {
        settingsActivity = Robolectric.buildActivity(SettingsActivity.class).create().get();
        assertEquals(false, settingsActivity.settings.isEnableNotifications());
        settingsActivity.notificationSwitch.performClick();
        assertEquals(true, settingsActivity.settings.isEnableNotifications());
        settingsActivity.notificationSwitch.performClick();
        assertEquals(false, settingsActivity.settings.isEnableNotifications());

    }

    @Test
    public void testUserBackgroundSync(){
        settingsActivity = Robolectric.buildActivity(SettingsActivity.class).create().get();
        assertEquals(false, settingsActivity.settings.isEnableBackgroundSync());
        settingsActivity.bSyncSwitch.performClick();
        assertEquals(true, settingsActivity.settings.isEnableBackgroundSync());
        settingsActivity.bSyncSwitch.performClick();
        assertEquals(false, settingsActivity.settings.isEnableBackgroundSync());
    }


    @Test
    public void testOnSeekBarChangedListener() {
        assertThat(shadow.getOnSeekBarChangeListener(), sameInstance(listener));
        seekBar.setOnSeekBarChangeListener(null);
        assertThat(shadow.getOnSeekBarChangeListener(), nullValue());
    }

    @Test
    public void testIfMaxDystanceIsSaving(){
        settingsActivity = Robolectric.buildActivity(SettingsActivity.class).create().get();
        pref = SharedPreferencesFactory.getSharedPref();
        settingsActivity.seekBar.setProgress(5);
        pref.edit().putInt("maxDistance", settingsActivity.seekBar.getProgress()).apply();
        assertEquals(5, pref.getInt("maxDistance",0));
    }



    private class TestSeekBarChangedListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
