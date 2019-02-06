package com.greenfox.gitinder.api.model;

import com.greenfox.gitinder.Constants;

import java.util.HashMap;
import java.util.Map;

public class TestSetting {

    Map<String, Boolean> endpoints;

    public TestSetting() {
        endpoints = new HashMap<>();
        endpoints.put(Constants.GET_PROFILES, false);
        endpoints.put(Constants.SWIPING, false);
        endpoints.put(Constants.GET_MATCHES, false);
        endpoints.put(Constants.GET_MESSAGES, false);
        endpoints.put(Constants.SEND_MESSAGE, false);
        endpoints.put(Constants.GET_SETTINGS, false);
        endpoints.put(Constants.SAVE_SETTINGS, false);
    }

    public boolean getStatus(String endpoint) {
        return endpoints.get(endpoint);
    }

    public void setStatus(String endpoint, boolean status) {
        endpoints.put(endpoint, status);
    }

    public Map<String, Boolean> getEndpoints() {
        return endpoints;
    }
}
