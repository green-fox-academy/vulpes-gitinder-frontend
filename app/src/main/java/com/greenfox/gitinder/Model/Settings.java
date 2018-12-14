package com.greenfox.gitinder.Model;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    boolean enable_notifications;
    boolean enable_background_sync;
    int max_distance;
    List<String> preferred_languages;

    public Settings() {
    }

    public Settings(boolean enable_notifications, boolean enable_background_sync, int max_distance, List<String> preffered_languages) {
        this.enable_notifications = enable_notifications;
        this.enable_background_sync = enable_background_sync;
        this.max_distance = max_distance;
        this.preferred_languages = preffered_languages;
    }

    public boolean isEnable_notifications() {
        return enable_notifications;
    }

    public void setEnable_notifications(boolean enable_notifications) {
        this.enable_notifications = enable_notifications;
    }

    public boolean isEnable_background_sync() {
        return enable_background_sync;
    }

    public void setEnable_background_sync(boolean enable_background_sync) {
        this.enable_background_sync = enable_background_sync;
    }

    public int getMax_distance() {
        return max_distance;
    }

    public void setMax_distance(int max_distance) {
        this.max_distance = max_distance;
    }

    public List<String> getPreferred_languages() {
        return preferred_languages;
    }

    public void setPreferred_languages(List<String> preferred_languages) {
        this.preferred_languages = preferred_languages;
    }

    public Settings createSettings(){
        List<String> languages = new ArrayList<>();
        languages.add("EN"); languages.add("CZ"); languages.add("HU");
        return new Settings(true, true, 20, languages);
    }

}
