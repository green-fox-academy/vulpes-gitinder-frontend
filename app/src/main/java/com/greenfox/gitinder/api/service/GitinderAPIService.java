package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.BuildConfig;
import com.greenfox.gitinder.Constants;
import com.greenfox.gitinder.api.model.TestSetting;

public class GitinderAPIService {

    GitinderAPI realAPI;
    GitinderAPI mockAPI;
    TestSetting settings;

    public GitinderAPIService(GitinderAPI realAPI, GitinderAPI mockAPI, TestSetting settings) {
        this.realAPI = realAPI;
        this.mockAPI = mockAPI;
        this.settings = settings;
    }

    public GitinderAPI provide(String endpoint) {
        if (BuildConfig.FLAVOR.equals("dev")) {
            if (endpoint.equals(Constants.GET_PROFILES_ENDPOINT)) {
                if (settings.isGetProfiles()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals(Constants.SWIPING_ENDPOINT)) {
                if (settings.isSwiping()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals(Constants.GET_MATCHES_ENDPOINT)) {
                if (settings.isMatches()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals(Constants.GET_MESSAGES_ENDPOINT)) {
                if (settings.isMessages()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals(Constants.SEND_MESSAGE_ENDPOINT)) {
                if (settings.isSendMessage()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals(Constants.GET_SETTINGS__ENDPOINT)) {
                if (settings.isGetSettings()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals(Constants.SAVE_SETTINGS_ENDPOINT)) {
                if (settings.isSetSettings()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else {
                return mockAPI;
            }
        } else {
            return realAPI;
        }
    }
}
