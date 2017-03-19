package com.hmmelton.kibbble.utils;

import com.hmmelton.kibbble.models.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harrisonmelton on 3/19/17.
 * This is a utility class for helping with dummy data.
 */

public class DummyDataUtil {

    private static List<Profile> mSavedProfiles = new ArrayList<>();

    /**
     * This method returns all saved profiles.
     * @return List of all saved profiles
     */
    public static List<Profile> getSavedProfiles() {
        return mSavedProfiles;
    }

    /**
     * This method adds a profile to the saved profiles.
     * @param profile Profile to be saved
     */
    public static void addSavedProfile(Profile profile) {
        mSavedProfiles.add(profile);
    }
}
