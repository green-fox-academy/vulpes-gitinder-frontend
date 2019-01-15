    package com.greenfox.gitinder.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profile {
    private static int counter = 0;

    public long id;
    String username;

    @SerializedName("avatar_url")
    String avatarUrl;

    List<String> repos;
    List<String> languages;
    List<String> snippets;

    public Profile() {
    }

    public Profile(String username, String avatarUrl, List<String> repos, List<String> languages, List<String> snippets) {
        this.id = counter++;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.repos = repos;
        this.languages = languages;
        this.snippets = snippets;
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

    public List<String> getRepos() {
        return repos;
    }

    public void setRepos(List<String> repos) {
        this.repos = repos;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<String> snippets) {
        this.snippets = snippets;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Profile.counter = counter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
