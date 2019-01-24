package com.greenfox.gitinder.activity.fragment.main;

import android.content.SharedPreferences;
import android.util.Log;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.mock.BackendMockAPI;
import com.greenfox.gitinder.api.model.GitinderResponse;
import com.greenfox.gitinder.factory.SharedPreferencesFactory;
import com.greenfox.gitinder.fragment.main.SettingsFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowSeekBar;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class SettingsFragmentTest {

    SettingsFragment settingsFragment;
    SharedPreferences pref;
    private ShadowSeekBar shadow;


    @Before
    public void setUp(){
        settingsFragment = new SettingsFragment();
        SupportFragmentTestUtil.startFragment(settingsFragment);
        pref = SharedPreferencesFactory.getSharedPref();
    }

    @Test
    public void settingsFragmentIsNotNull() {
        assertNotNull(settingsFragment);
    }

    @Test
    public void testIfNotificationsSettingsIsSaving() {
        assertEquals(false, pref.getBoolean("enableNotifications", false));
        settingsFragment.notificationSwitch.performClick();
        assertEquals(true, pref.getBoolean("enableNotifications", false));
        settingsFragment.notificationSwitch.performClick();
        assertEquals(false, pref.getBoolean("enableNotifications", false));
    }

    @Test
    public void testIfBckSyncSettingsIsSaving() {
        assertEquals(false, pref.getBoolean("enableBackgroundSync", false));
        settingsFragment.bSyncSwitch.performClick();
        assertEquals(true, pref.getBoolean("enableBackgroundSync", false));
        settingsFragment.bSyncSwitch.performClick();
        assertEquals(false, pref.getBoolean("enableBackgroundSync", false));
    }

    @Test
    public void testUserNotifications() {
        assertEquals(false, settingsFragment.settings.isEnableNotifications());
        settingsFragment.notificationSwitch.performClick();
        assertEquals(true, settingsFragment.settings.isEnableNotifications());
        settingsFragment.notificationSwitch.performClick();
        assertEquals(false, settingsFragment.settings.isEnableNotifications());

    }

    @Test
    public void testUserBackgroundSync() {
        assertEquals(false, settingsFragment.settings.isEnableBackgroundSync());
        settingsFragment.bSyncSwitch.performClick();
        assertEquals(true, settingsFragment.settings.isEnableBackgroundSync());
        settingsFragment.bSyncSwitch.performClick();
        assertEquals(false, settingsFragment.settings.isEnableBackgroundSync());
    }

    @Test
    public void testIfMaxDystanceIsSaving() {
        settingsFragment.seekBar.setProgress(5);
        shadow = Shadows.shadowOf(settingsFragment.seekBar);
        shadow.getOnSeekBarChangeListener().onStopTrackingTouch(settingsFragment.seekBar);
        assertEquals(5, pref.getInt("maxDistance", 0));
    }

    @Test
    public void logoutTest(){
        pref.edit().putString(Constants.GITINDER_TOKEN, "MOJE MATKA JE PIVO PROSIM").apply();
        settingsFragment.logout();

        assertTrue(!pref.contains(Constants.GITINDER_TOKEN));
    }

}
