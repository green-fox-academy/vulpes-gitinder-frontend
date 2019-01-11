package com.greenfox.gitinder.activity.fragment.main;

import com.greenfox.gitinder.fragment.main.MatchesFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class MatchesFragmentTest {

    @Test
    public void fragmentIsNotNull() throws Exception {
        MatchesFragment matchesFragment = new MatchesFragment();
        SupportFragmentTestUtil.startFragment(matchesFragment);

        assertNotNull(matchesFragment);
    }

}
