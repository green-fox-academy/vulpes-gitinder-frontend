package com.greenfox.gitinder.model;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

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
