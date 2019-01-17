package com.greenfox.gitinder.activity.fragment.main;

import com.greenfox.gitinder.fragment.main.SwipingFragment;
import com.squareup.picasso.MockPicasso;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class SwipingFragmentTest {

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
    }

    @Test
    public void swipingFragmentIsNotNull() {
        MockPicasso.init();

        SwipingFragment swipingFragment = new SwipingFragment();
        SupportFragmentTestUtil.startFragment(swipingFragment);

        assertNotNull(swipingFragment);
    }
}
