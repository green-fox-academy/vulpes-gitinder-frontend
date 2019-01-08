package com.greenfox.gitinder.model;

import java.util.List;

public class Matches {

    List<Match> matches;

    public Matches() {
    }

    public Matches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
