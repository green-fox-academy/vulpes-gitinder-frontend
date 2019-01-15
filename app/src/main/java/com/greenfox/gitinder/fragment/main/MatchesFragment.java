package com.greenfox.gitinder.fragment.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.BaseFragment;

public class MatchesFragment extends BaseFragment {
    private static final String TAG = "MatchesFragment";

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);

        recyclerView = view.findViewById(R.id.fragment_matches_recycler_view);

        return view;
    }

    public void loadMatches(){
        
    }
}
