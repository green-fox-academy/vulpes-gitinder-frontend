package com.greenfox.gitinder.api.model.factory;

import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.factory.ProfileFactory;

import java.util.ArrayList;
import java.util.List;

public class AvailableProfilesFactory {

    public static AvailableProfiles createAvailableProfiles() {
        List<Profile> profiles = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            profiles.add(ProfileFactory.createProfile());
        }
        AvailableProfiles availableProfiles = new AvailableProfiles();
        availableProfiles.setProfiles(profiles);
        availableProfiles.setCount(profiles.size());
        availableProfiles.setAll(profiles.size());

        return availableProfiles;
    }
}
