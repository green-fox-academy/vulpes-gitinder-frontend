package com.greenfox.gitinder.activity.mainActivityFragments;

import com.greenfox.gitinder.mainActivityFragment.SwipingFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class SwipingFragmentTest {

    @Test
    public void settingsFragmentIsNotNull() throws Exception {
//        MockPicasso.init();


        SwipingFragment swipingFragment = new SwipingFragment();
        SupportFragmentTestUtil.startFragment(swipingFragment);

        assertNotNull(swipingFragment);
    }

}
