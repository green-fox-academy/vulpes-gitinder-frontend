package com.greenfox.gitinder.api.model;

import com.greenfox.gitinder.model.Profile;

import java.util.List;

public class AvailableProfiles {

    List<Profile> profiles;
    Integer count;
    Integer all;

    public AvailableProfiles() {
    }

    public AvailableProfiles(List<Profile> profiles, Integer count, Integer all) {
        this.profiles = profiles;
        this.count = count;
        this.all = all;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
