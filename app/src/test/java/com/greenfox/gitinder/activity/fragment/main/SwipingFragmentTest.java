package com.greenfox.gitinder.activity.fragment.main;

import com.squareup.picasso.MockPicasso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class SwipingFragmentTest {

    @Test
    public void settingsFragmentIsNotNull() throws Exception {
        MockPicasso.init();

        SwipingFragment swipingFragment = new SwipingFragment();
        SupportFragmentTestUtil.startFragment(swipingFragment);

        assertNotNull(swipingFragment);
    }

}
