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
            if (settings.getEndpoints().containsKey(endpoint) && settings.getStatus(endpoint)) {
                return realAPI;
            }
            return mockAPI;
        }
        return realAPI;
    }
}
