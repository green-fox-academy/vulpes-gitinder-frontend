package com.greenfox.gitinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Test
    public void helloWorldTest() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        assertEquals("Hello World!", mainActivity.hello.getText());
    }

}