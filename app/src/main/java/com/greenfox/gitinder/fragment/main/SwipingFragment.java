package com.greenfox.gitinder.fragment.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.BaseFragment;
import com.squareup.picasso.Picasso;

public class SwipingFragment extends BaseFragment {
    private static final String TAG = "SwipingFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swiping_fragment, container, false);
        ImageView profilePic = view.findViewById(R.id.main_profile_picture);
        Picasso.get().load("https://www.randomlists.com/img/people/tom_hanks.jpg").into(profilePic);
        return view;
    }
}
