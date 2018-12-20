package com.greenfox.gitinder.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Match {

    String username;

    @SerializedName("avatar_url")
    String avatarUrl;

    @SerializedName("matched_at")
    int matchedAt;

    List<Message> messages;

    public Match() {
    }

    public Match(String username, String avatarUrl, int matchedAt, List<Message> messages) {
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.matchedAt = matchedAt;
        this.messages = messages;
    }

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

    public int getMatchedAt() {
        return matchedAt;
    }

    public void setMatchedAt(int matchedAt) {
        this.matchedAt = matchedAt;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
