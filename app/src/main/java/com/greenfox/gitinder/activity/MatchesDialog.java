package com.greenfox.gitinder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.fragment.main.SwipingFragment;
import com.greenfox.gitinder.model.Match;
import com.squareup.picasso.Picasso;

public class MatchesDialog extends DialogFragment {

    private TextView chat,swipe;
    private ImageView imageView;
    Match match;

    public void setMatch(Match match) {
        this.match = match;
    }

    private static final String TAG = "MatchesDialog";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_matches, container,false);
        chat = view.findViewById(R.id.matches_dialog_chat);
        swipe = view.findViewById(R.id.matches_dialog_swipe);
        imageView = view.findViewById(R.id.imageView);
        Picasso.get().load(match.getAvatarUrl()).into(imageView);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MessagesActivity.class);
                intent.putExtra("match",match);
                getDialog().dismiss();
                startActivity(intent);

            }
        });

        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { getDialog().dismiss();
            }
        });
        return view;
    }
}
