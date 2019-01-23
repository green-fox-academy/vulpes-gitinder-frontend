package com.greenfox.gitinder.api.model;

public class SnippetRequest {
    String url;
    String snippet;

    public SnippetRequest(String url, String snippet) {
        this.url = url;
        this.snippet = snippet;
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
}
