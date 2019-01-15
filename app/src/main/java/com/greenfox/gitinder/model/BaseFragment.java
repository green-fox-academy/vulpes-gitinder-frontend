package com.greenfox.gitinder.model;

import dagger.android.support.DaggerFragment;

public class BaseFragment extends DaggerFragment {

    String title;

    public BaseFragment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
