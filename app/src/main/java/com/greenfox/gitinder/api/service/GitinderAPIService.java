package com.greenfox.gitinder.api.service;

import com.greenfox.gitinder.BuildConfig;
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
            if (endpoint.equals("getprofiles")) {
                if (settings.isGetProfiles()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals("swiping")) {
                if (settings.isSwiping()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals("getmatches")) {
                if (settings.isMatches()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals("getmessages")) {
                if (settings.isMessages()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals("sendmessage")) {
                if (settings.isSendMessage()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals("getsettings")) {
                if (settings.isGetSettings()) {
                    return realAPI;
                } else {
                    return mockAPI;
                }
            } else if (endpoint.equals("savesettings")) {
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
