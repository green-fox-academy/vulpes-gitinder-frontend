package com.greenfox.gitinder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.Match;

public class MatchAdapter extends ArrayAdapter<Match> {
    public MatchAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    final Match current = getItem(position);

    convertView = LayoutInflater.from(getContext()).inflate(R.layout.match, parent, false);

        TextView usernameText = convertView.findViewById(R.id.match_username);
        TextView interestsText = convertView.findViewById(R.id.match_interests);
        Button messagesButton = convertView.findViewById(R.id.match_messages_button);
        Button profileButton = convertView.findViewById(R.id.match_profile_button);

        messagesButton.setOnClickListener(v -> Toast.makeText(v.getContext(), "PIVO PROSIM", Toast.LENGTH_SHORT).show());

        profileButton.setOnClickListener(v -> Toast.makeText(v.getContext(), "TOTO JE MOJE MATKA", Toast.LENGTH_SHORT).show());

        if (current != null && usernameText != null){
            usernameText.setText(current.getUsername());
        }

        if (current != null && interestsText != null){
            interestsText.setText("java, ruby, js...");
        }

        return super.getView(position, convertView, parent);
    }
}
