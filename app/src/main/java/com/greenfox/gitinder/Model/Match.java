package com.greenfox.gitinder.Model;

import java.util.List;

public class Match {

    String username;
    String avatar_url;
    int matched_at;
    List<Message> messages;

    public Match() {
    }

    public Match(String username, String avatar_url, int matched_at, List<Message> messages) {
        this.username = username;
        this.avatar_url = avatar_url;
        this.matched_at = matched_at;
        this.messages = messages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getMatched_at() {
        return matched_at;
    }

    public void setMatched_at(int matched_at) {
        this.matched_at = matched_at;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
