package com.greenfox.gitinder.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Settings {

    @SerializedName("enable_notifications")
    boolean enableNotifications;

    @SerializedName("enable_background_sync")
    boolean enableBackgroundSync;

    @SerializedName("max_distance")
    int maxDistance;

    @SerializedName("preferred_languages")
    List<String> preferredLanguages;

    public Settings() {
    }

    public Settings(boolean enableNotifications, boolean enableBackgroundSync, int maxDistance, List<String> preferredLanguages) {
        this.enableNotifications = enableNotifications;
        this.enableBackgroundSync = enableBackgroundSync;
        this.maxDistance = maxDistance;
        this.preferredLanguages = preferredLanguages;
    }

    public boolean isEnableNotifications() {
        return enableNotifications;
    }

    public void setEnableNotifications(boolean enableNotifications) {
        this.enableNotifications = enableNotifications;
    }

    public boolean isEnableBackgroundSync() {
        return enableBackgroundSync;
    }

    public void setEnableBackgroundSync(boolean enableBackgroundSync) {
        this.enableBackgroundSync = enableBackgroundSync;
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
