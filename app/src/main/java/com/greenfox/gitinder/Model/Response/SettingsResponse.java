package com.greenfox.gitinder.Model.Response;

import com.greenfox.gitinder.Model.Settings;

public class SettingsResponse extends GitinderResponse {

    Settings settings;

    public SettingsResponse() {
    }

    public SettingsResponse(String status, String message, Settings settings) {
        super(status, message);
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
