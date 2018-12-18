package com.greenfox.gitinder.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSettings {

    @SerializedName("enable_notifications")
    boolean isEnableNotification;
    @SerializedName("enable_background_sync")
    boolean isEnableBackgroundSync;
    @SerializedName("max_distance")
    int maxDistance;
    @SerializedName("preferred_languages")
    List<String> preferredLanguages;

    public UserSettings() {
    }

    public UserSettings(List<String> preferredLanguages) {
        this.isEnableNotification = false;
        this.isEnableBackgroundSync = false;
        this.maxDistance = 0;
        this.preferredLanguages = preferredLanguages;
    }

    public boolean isEnableNotification() {
        return isEnableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        isEnableNotification = enableNotification;
    }

    public boolean isEnableBackgroundSync() {
        return isEnableBackgroundSync;
    }

    public void setEnableBackgroundSync(boolean enableBackgroundSync) {
        isEnableBackgroundSync = enableBackgroundSync;
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
