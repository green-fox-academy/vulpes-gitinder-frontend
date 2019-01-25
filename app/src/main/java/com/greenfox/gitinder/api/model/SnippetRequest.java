package com.greenfox.gitinder.api.model;

import com.greenfox.gitinder.api.service.SnippetService;

public class SnippetRequest {
    String url;
    String snippet;
    SnippetService.SnippetListener fragment;

    public SnippetRequest(String url, String snippet, SnippetService.SnippetListener fragment) {
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

    public SnippetService.SnippetListener getFragment() {
        return fragment;
    }

    public void setFragment(SnippetService.SnippetListener fragment) {
        this.fragment = fragment;
    }
}
