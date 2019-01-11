package com.greenfox.gitinder.model;

import android.support.v4.app.Fragment;

public class FragmentAndTitle {

    Fragment fragment;
    String title;

    public FragmentAndTitle(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
