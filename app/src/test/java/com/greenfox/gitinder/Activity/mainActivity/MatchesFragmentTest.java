package com.greenfox.gitinder.activity.mainActivity;

import com.greenfox.gitinder.mainActivityFragment.MatchesFragment;

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
