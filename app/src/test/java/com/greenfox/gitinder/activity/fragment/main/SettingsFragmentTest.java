package com.greenfox.gitinder.activity.fragment.main;

import com.greenfox.gitinder.fragment.main.SettingsFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class SettingsFragmentTest {

    @Test
    public void settingsFragmentIsNotNull() throws Exception {
        SettingsFragment settingsFragment = new SettingsFragment();
        SupportFragmentTestUtil.startFragment(settingsFragment);

        assertNotNull(settingsFragment);
    }

}