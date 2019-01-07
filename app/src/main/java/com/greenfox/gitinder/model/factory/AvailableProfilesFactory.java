package com.greenfox.gitinder.model.factory;

import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class AvailableProfilesFactory {

    public static AvailableProfiles createAvailableProfiles() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(ProfileFactory.createProfile("userOne"));
        profiles.add(ProfileFactory.createProfile("userTwo"));
        profiles.add(ProfileFactory.createProfile("UserThree"));
        AvailableProfiles availableProfiles = new AvailableProfiles();
        availableProfiles.setProfiles(profiles);
        availableProfiles.setCount(3);
        availableProfiles.setAll(3);

        return availableProfiles;
    }
}
