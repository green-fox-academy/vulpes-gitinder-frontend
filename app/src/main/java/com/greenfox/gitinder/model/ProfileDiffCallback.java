package com.greenfox.gitinder.model;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class ProfileDiffCallback extends DiffUtil.Callback  {

    private final List<Profile> oldList;
    private final List<Profile> newList;

    public ProfileDiffCallback(List<Profile> oldList, List<Profile> newList){
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition).id == newList.get(newPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        Profile oldProfile = oldList.get(oldPosition);
        Profile newProfile = newList.get(newPosition);
        return oldProfile.username.equals(newProfile.username)
                && oldProfile.languages.equals(newProfile.languages)
                && oldProfile.avatarUrl.equals(newProfile.avatarUrl)
                && oldProfile.repos.equals(newProfile.repos)
                && oldProfile.snippets.equals(newProfile.snippets);
    }
}
