package com.greenfox.gitinder.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Match implements Parcelable {

    String username;

    @SerializedName("avatar_url")
    String avatarUrl;

    @SerializedName("matched_at")
    String matchedAt;

    List<Message> messages;

    public Match() {
    }

    public Match(String username, String avatarUrl, String matchedAt, List<Message> messages) {
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.matchedAt = matchedAt;
        this.messages = messages;
    }

    protected Match(Parcel in) {
        username = in.readString();
        avatarUrl = in.readString();
        matchedAt = in.readString();
        messages = new ArrayList<>();
        in.readList(messages, Message.class.getClassLoader());
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMatchedAt() {
        return matchedAt;
    }

    public void setMatchedAt(String matchedAt) {
        this.matchedAt = matchedAt;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getLastMessage(){
        return messages.get(messages.size() - 1).getMessage();
    }

    public boolean isNew(){
        return messages.size() < 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatarUrl);
        dest.writeString(matchedAt);
        dest.writeList(messages);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Match){
            return ((Match) obj).getUsername().equals(username);
        }
        return false;
    }

}
