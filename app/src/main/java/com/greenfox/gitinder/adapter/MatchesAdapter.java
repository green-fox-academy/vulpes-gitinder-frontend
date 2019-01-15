package com.greenfox.gitinder.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.greenfox.gitinder.model.Match;

public class MatchesAdapter extends ArrayAdapter<Match> {

    public MatchesAdapter(Context context, int resource) {
        super(context, resource);
    }
}
