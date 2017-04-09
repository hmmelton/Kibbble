package com.hmmelton.kibbble.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hmmelton.kibbble.KibbbleApplication;
import com.hmmelton.kibbble.models.Filters;
import com.hmmelton.kibbble.models.User;

/**
 * Created by harrisonmelton on 3/18/17.
 * Utility file for SharedPreferences
 */

public class SharedPrefsUtil {
    // SharedPreferences and Gson setup
    private static final String PREFS_NAME = "sonora_preferences";
    private static final SharedPreferences mPrefs =
            KibbbleApplication.getInstance().getSharedPreferences(PREFS_NAME, 0);
    private static final SharedPreferences.Editor mEditor = mPrefs.edit();
    private static final Gson mGson = new Gson();

    // Key strings
    private static final String USER_KEY = "user";
    private static final String FILTERS_KEY = "pet_filters";
    /*
     * Save to local storage
     */

    /**
     * This method saves a user to local storage.
     * @param user User to be saved
     */
    public static void saveUser(User user) {
        String json = mGson.toJson(user);
        mEditor.putString(USER_KEY, json);
        mEditor.commit();
    }

    /**
     * This method saves pet filters to local storage.
     * @param filters filters to save
     */
    public static void savePetFilter(Filters filters) {
        String filtersJson = mGson.toJson(filters, Filters.class);
        mEditor.putString(FILTERS_KEY, filtersJson);
        // Save data to local storage
        mEditor.commit();
    }

    /*
     * Fetch from local storage
     */

    /**
     * This method returns the current user form local storage.
     * @return Currently-signed in user
     */
    public static User getUser() {
        String json = mPrefs.getString(USER_KEY, null);
        return json == null ? null : mGson.fromJson(json, User.class);
    }

    /**
     * This method returns saved filters for pets.
     * @return Set of filters for available pets
     */
    public static Filters getPetFilters() {
        String json = mPrefs.getString(FILTERS_KEY, null);
        return json == null ? null : mGson.fromJson(json, Filters.class);
    }

    /**
     * This method signs the current user out of local storage.
     */
    public static void signOut() {
        mEditor.remove(USER_KEY);
        mEditor.commit();
    }
}
