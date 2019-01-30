package com.greenfox.gitinder.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.model.factory.MessagesFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Match> matchList;

    public MatchAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.matchList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.match, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String username = matchList.get(position).getUsername();
        String avatarUrl = matchList.get(position).getAvatarUrl();

        String lastMessage;
        if(matchList.get(position).getMessages().size() == 0){
            lastMessage = "";
        } else {
            lastMessage = matchList.get(position).getLastMessage();
        }

        if(matchList.get(position).isNew()){
            holder.newText.setText("NEW");
        } else {
            holder.newText.setText("");
        }

        holder.messagesText.setText(lastMessage);
        holder.usernameText.setText(username);
        Picasso.get().load(avatarUrl).into(holder.profilePicture);
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Inject
        SharedPreferences sharedPreferences;

        TextView usernameText;
        TextView messagesText;
        TextView newText;
        Button messagesButton;
        Button profileButton;
        ImageView profilePicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.match_username);
            messagesText = itemView.findViewById(R.id.match_messages);
            newText = itemView.findViewById(R.id.match_new_text);
            messagesButton = itemView.findViewById(R.id.match_messages_button);
            profileButton = itemView.findViewById(R.id.match_profile_button);
            profilePicture = itemView.findViewById(R.id.match_picture);

            if (BuildConfig.FLAVOR.equals("dev")) {
                messagesButton.setOnClickListener(v -> {
                    setMessageToMatch(matchList.get(getAdapterPosition()));
                    notifyDataSetChanged();
                });
            } else {
                messagesButton.setOnClickListener(v -> Toast.makeText(v.getContext(), "PIVO PROSIM", Toast.LENGTH_SHORT).show());
            }

            profileButton.setOnClickListener(v -> Toast.makeText(v.getContext(), "TOTO JE MOJE MATKA", Toast.LENGTH_SHORT).show());
        }

    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void addMatches(List<Match> matches){
        matchList.addAll(matches);
    }

    public void clearMatches(){
        matchList.clear();
    }

    public void setMessageToMatch(Match match){
        match.setMessages(MessagesFactory.createMessage());
    }

    public int matchesWithNoMessage(){
        int counter = 0;
        for (Match match: getMatchList()){
            if(match.getMessages().size() < 1){
                counter++;
            }
        }
        return counter;
    }
}
