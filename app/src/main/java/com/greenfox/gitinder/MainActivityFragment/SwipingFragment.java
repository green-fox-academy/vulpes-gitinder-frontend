package com.greenfox.gitinder.mainActivityFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.greenfox.gitinder.MainActivity;
import com.greenfox.gitinder.R;
import com.squareup.picasso.Picasso;

public class SwipingFragment extends Fragment {
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
