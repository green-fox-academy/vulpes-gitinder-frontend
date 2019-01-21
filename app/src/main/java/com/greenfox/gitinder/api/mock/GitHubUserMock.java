package com.greenfox.gitinder.api.mock;

import com.greenfox.gitinder.api.model.GitHubUsername;
import com.greenfox.gitinder.api.service.GithubUserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubUserMock implements GithubUserAPI {

    @Override
    public Call<GitHubUsername> getGitHubUsername(String gitHubToken) {
        return new CallMock<GitHubUsername>() {
            @Override
            public void enqueue(Callback<GitHubUsername> callback) {
                callback.onResponse(this, Response.success(new GitHubUsername("Joey")));
            }
        };
    }
}
