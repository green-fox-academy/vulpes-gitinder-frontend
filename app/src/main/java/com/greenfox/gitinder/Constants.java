package com.greenfox.gitinder;


import javax.inject.Singleton;

public class Constants {
    public static final String SHARED_PREFERENCES = "preference";
    public static final String GITINDER_TOKEN = "gitinder-token";
    public static final String GITHUB_CLIENT_ID = BuildConfig.gitHubClientId;
    public static final String GITHUB_CLIENT_SECRET = BuildConfig.gitHubClientSecret;
    public static final String GITHUB_CALLBACK = BuildConfig.gitHubCallback;
    public static final String GITHUB_URL = "https://github.com/login/oauth/authorize?githubAPI_id=";
    public static final String ENABLE_NOTIFICATIONS = "enableNotifications";
    public static final String ENABLE_BACKGROUNDSYNC = "enableBackgroundSync";
    public static final String MAX_DISTANCE = "maxDistance";
    public static final String NOTIFICATION_CHANNEL = "Notifications";
    public static final String MATCHES_COUNT = "matchesCount";
    public static final String NEW_MATCH_GROUP = "newMatches";
    public static final String GO_TO_MATCHES = "GoToMatches";
    public static final String PROFILE = "profile";
    public static final String USERNAME = "username";
    public static final String GET_PROFILES = "Get Profiles";
    public static final String SWIPING = "Swiping";
    public static final String GET_MATCHES = "Get Matches";
    public static final String GET_MESSAGES = "GET Messages";
    public static final String SEND_MESSAGE = "Send Message";
    public static final String GET_SETTINGS = "Get Settings";
    public static final String SAVE_SETTINGS = "Save Settings";
    public static final String GITINDER_API_URL = "https://gitinder.azurewebsites.net";

}
