package com.greenfox.gitinder.api.model;

import com.google.gson.annotations.SerializedName;

public class GitHubUsername {

    String login;

    @SerializedName("avatar_url")
    String avatarUrl;

    public GitHubUsername() {
    }

    public GitHubUsername(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
