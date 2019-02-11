package com.greenfox.gitinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.R;
import com.greenfox.gitinder.activity.MessagesActivity;
import com.greenfox.gitinder.activity.ProfileActivity;
import com.greenfox.gitinder.api.model.CustomCallback;
import com.greenfox.gitinder.api.service.GitinderAPI;
import com.greenfox.gitinder.api.service.GitinderAPIService;
import com.greenfox.gitinder.model.Match;
import com.greenfox.gitinder.api.service.MatchService;
import com.greenfox.gitinder.model.Profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> implements MatchService.MatchesListener{
    private static final String TAG = "MatchAdapter";

    private LayoutInflater mInflater;
    List<Match> matchList;
    MatchService matchService;
    GitinderAPIService gitinderAPI;
    SharedPreferences sharedPreferences;

    public MatchAdapter(Context context, MatchService matchService, GitinderAPIService gitinderAPI, SharedPreferences sharedPreferences) {
        this.mInflater = LayoutInflater.from(context);
        this.matchList = new ArrayList<>();
        this.gitinderAPI = gitinderAPI;
        this.sharedPreferences = sharedPreferences;
        this.matchService = matchService;
        matchService.setMatchesListener(this);
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

        String lastMessage = getLastMatchMessage(position);

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

    @Override
    public void onMatchesChanged(List<Match> updatedMatches) {
        clearMatches();
        addMatches(updatedMatches);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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

            messagesButton.setOnClickListener(v -> {
                Intent intentMessages = new Intent(itemView.getContext(), MessagesActivity.class);
                intentMessages.putExtra("match", matchList.get(getAdapterPosition()));
                intentMessages.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intentMessages);
            });

            profileButton.setOnClickListener(v -> {
                Intent profileIntent = new Intent(itemView.getContext(), ProfileActivity.class);
                Call<Profile> profileCall = gitinderAPI.provide(Constants.GET_TARGET_PROFILE)
                        .getTargetProfile(sharedPreferences.getString(Constants.GITINDER_TOKEN, ""),
                                          matchList.get(getAdapterPosition()).getUsername());
                profileCall.enqueue(new CustomCallback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        Log.d(TAG, "onResponse: SUCCESS");
                        profileIntent.putExtra(Constants.PROFILE, response.body());
                        profileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        itemView.getContext().startActivity(profileIntent);
                    }
                });

            });
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

    public String getLastMatchMessage(int viewHolderPosition){
        String last;
        if(matchList.get(viewHolderPosition).getMessages().size() == 0){
            last = "";
        } else {
            last = matchList.get(viewHolderPosition).getLastMessage();
        }
        return last;
    }

}
