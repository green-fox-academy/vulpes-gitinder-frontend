package com.greenfox.gitinder;

import com.greenfox.gitinder.activities.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Test
    public void helloWorldTest() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

}
