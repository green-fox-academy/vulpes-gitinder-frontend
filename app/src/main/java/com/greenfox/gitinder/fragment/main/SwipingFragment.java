package com.greenfox.gitinder.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activity.ProfileActivity;
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
        TextView like = view.findViewById(R.id.main_like);
        if (BuildConfig.FLAVOR.equals("dev")) {
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;


    }
}
