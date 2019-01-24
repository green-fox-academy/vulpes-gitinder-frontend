package com.greenfox.gitinder.api.model;

import com.greenfox.gitinder.activity.SnippetListener;

public class SnippetRequest {
    String url;
    String snippet;
    SnippetListener fragment;

    public SnippetRequest(String url, String snippet, SnippetListener fragment) {
        this.url = url;
        this.snippet = snippet;
        this.fragment = fragment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public SnippetListener getFragment() {
        return fragment;
    }

    public void setFragment(SnippetListener fragment) {
        this.fragment = fragment;
    }
}
