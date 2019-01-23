package com.greenfox.gitinder.api.model.factory;

import com.greenfox.gitinder.api.model.AvailableProfiles;
import com.greenfox.gitinder.model.Profile;
import com.greenfox.gitinder.model.factory.ProfileFactory;

import java.util.ArrayList;
import java.util.List;

public class AvailableProfilesFactory {

    public static AvailableProfiles createAvailableProfiles() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(ProfileFactory.createProfileWithPicture("userOne", "https://static.appvn.com/a/uploads/thumbnails/112015/mr-square_icon.png"));
        profiles.add(ProfileFactory.createProfileWithPicture("userTwo", "https://openclipart.org/image/2400px/svg_to_png/230062/Square-Smiley.png"));
        profiles.add(ProfileFactory.createProfileWithPicture("UserThree", "https://upload.wikimedia.org/wikipedia/commons/e/ec/Happy_smiley_face.png"));
        AvailableProfiles availableProfiles = new AvailableProfiles();
        availableProfiles.setProfiles(profiles);
        availableProfiles.setCount(3);
        availableProfiles.setAll(3);

        return availableProfiles;
    }
}
