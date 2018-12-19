package com.greenfox.gitinder.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSettings {

    @SerializedName("enable_notifications")
    boolean EnableNotification;
    @SerializedName("enable_background_sync")
    boolean EnableBackgroundSync;
    @SerializedName("max_distance")
    int maxDistance;
    @SerializedName("preferred_languages")
    List<String> preferredLanguages;

    public UserSettings() {
    }

    public UserSettings(List<String> preferredLanguages) {
        this.EnableNotification = false;
        this.EnableBackgroundSync = false;
        this.maxDistance = 0;
        this.preferredLanguages = preferredLanguages;
    }

    public boolean isEnableNotification() {
        return EnableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        EnableNotification = enableNotification;
    }

    public boolean isEnableBackgroundSync() {
        return EnableBackgroundSync;
    }

    public void setEnableBackgroundSync(boolean enableBackgroundSync) {
        EnableBackgroundSync = enableBackgroundSync;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public List<String> getPreferredLanguages() {
        return preferredLanguages;
    }

    public void setPreferredLanguages(List<String> preferredLanguages) {
        this.preferredLanguages = preferredLanguages;
    }
}
