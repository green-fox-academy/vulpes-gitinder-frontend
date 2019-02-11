package com.greenfox.gitinder.model;

import java.util.List;

public class Languages {

    List<String> languages;

    public Languages() {
    }

    public Languages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
