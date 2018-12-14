package com.greenfox.gitinder.Model;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    String username;
    String avatar_url;
    List<String> repos;
    List<String> languages;
    List<String> snippets;

    public Profile() {
    }

    public Profile(String username, String avatar_url, List<String> repos, List<String> languages, List<String> snippets) {
        this.username = username;
        this.avatar_url = avatar_url;
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
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

    public Profile createProfile(String username){
        List<String> repos = new ArrayList<>();
        repos.add("repo1"); repos.add("repo2"); repos.add("repo3");
        List<String> languages = new ArrayList<>();
        languages.add("EN"); languages.add("CZ"); languages.add("HU");
        List<String> snippets = new ArrayList<>();
        snippets.add("snippet1"); snippets.add("snippet2"); snippets.add("snippet3");

        return new Profile(username, "/saddgrwehreberv", repos, languages, snippets);
    }
}
