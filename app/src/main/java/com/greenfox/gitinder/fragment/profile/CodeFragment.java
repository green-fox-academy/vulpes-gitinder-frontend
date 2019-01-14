package com.greenfox.gitinder.fragment.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.BaseFragment;

public class CodeFragment extends BaseFragment {
    private static final String TAG = "CodeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.code_fragment, container, false);
        Log.d(TAG, "onCreateView: created");
        return view;
    }
}
