package com.greenfox.gitinder.api.mock;

import com.greenfox.gitinder.api.model.GitHubToken;
import com.greenfox.gitinder.api.service.GithubTokenAPI;

import retrofit2.Callback;
import retrofit2.Response;

public class GitHubMock implements GithubTokenAPI {

    @Override
    public CallMock<GitHubToken> getToken(String ClientID, String secret, final String code) {
        return new CallMock<GitHubToken>() {
            @Override
            public void enqueue(Callback<GitHubToken> callback) {
                if (code.equals("7fd23c00de517e3b78c2")) {
                    callback.onResponse(this, Response.success(new GitHubToken("aaa", "bbb")));
                }
            }
        };
    }

//    @Override
//    public CallMock<GitHubUsername> getGitHubUsername(String gitHubToken) {
//        return new CallMock<GitHubUsername>() {
//            @Override
//            public void enqueue(Callback<GitHubUsername> callback) {
//                callback.onResponse(this, Response.success(new GitHubUsername("Joey")));
//            }
//        };
//    }
}
