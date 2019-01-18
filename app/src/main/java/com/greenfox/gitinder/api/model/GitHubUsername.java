package com.greenfox.gitinder.api.model;

public class GitHubUsername {

    String login;

    public GitHubUsername() {
    }

    public GitHubUsername(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
