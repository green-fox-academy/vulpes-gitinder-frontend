package com.greenfox.gitinder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.gitinder.R;
import com.greenfox.gitinder.model.Profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Profile> profiles;

    public CardStackAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.profiles = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Profile profile = profiles.get(position);
        holder.username.setText(profile.getUsername());
        Picasso.get().load(profile.getAvatarUrl()).into(holder.image);

        holder.itemView.setOnClickListener
                (v -> Toast.makeText(v.getContext(), profile.getUsername(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        ImageView image;
        ViewHolder(View view) {
            super(view);
            this.username = view.findViewById(R.id.item_name);
            this.image = view.findViewById(R.id.item_image);
        }
    }

    public void addProfiles(List<Profile> profileList){
        profiles.addAll(profileList);
    }

}
