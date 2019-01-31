package com.greenfox.gitinder.api.model;

import com.greenfox.gitinder.model.Match;

public class SwipeResponse extends GitinderResponse {

    Match match;

    public SwipeResponse() {
    }

    public SwipeResponse(String status, String message, Match match) {
        super(status, message);
        this.match = match;
    }

    public SwipeResponse(String status, String message) {
        super(status, message);
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
