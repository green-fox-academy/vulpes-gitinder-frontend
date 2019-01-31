package com.greenfox.gitinder.activity.fragment.main;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activity.MainActivity;
import com.greenfox.gitinder.factory.SharedPreferencesFactory;
import com.greenfox.gitinder.fragment.main.MatchesFragment;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class MatchesFragmentTest {

    MatchesFragment matchesFragment;

    @Before
    public void setUp(){
        matchesFragment = new MatchesFragment();
        SupportFragmentTestUtil.startFragment(matchesFragment);
    }

    @Test
    public void fragmentIsNotNull() throws Exception {
        assertNotNull(matchesFragment);
    }

}
