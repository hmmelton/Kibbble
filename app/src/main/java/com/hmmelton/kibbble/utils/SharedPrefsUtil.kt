package com.hmmelton.kibbble.utils

import com.google.gson.Gson
import com.hmmelton.kibbble.KibbbleApplication
import com.hmmelton.kibbble.models.Filters
import com.hmmelton.kibbble.models.User

/**
 * Created by harrisonmelton on 4/30/17.
 * Utility file for interacting with SharedPreferences.
 */
object SharedPrefsUtil {
    // SharedPreferences and Gson setup
    private val PREFS_NAME = "sonora_preferences"
    private val mPrefs = KibbbleApplication.getInstance().getSharedPreferences(PREFS_NAME, 0)
    private val mEditor = mPrefs.edit()
    private val mGson = Gson()

    // Key strings
    private val USER_KEY = "user"
    private val FILTERS_KEY = "pet_filters"
    /*
     * Save to local storage
     */

    /**
     * This method saves a user to local storage.
     * @param user User to be saved
     */
    fun saveUser(user: User) {
        val json = mGson.toJson(user)
        mEditor.putString(USER_KEY, json)
        mEditor.commit()
    }

    /**
     * This method saves pet filters to local storage.
     * @param filters filters to save
     */
    fun savePetFilter(filters: Filters) {
        val filtersJson = mGson.toJson(filters, Filters::class.java)
        mEditor.putString(FILTERS_KEY, filtersJson)
        // Save data to local storage
        mEditor.commit()
    }

    /*
     * Fetch from local storage
     */

    /**
     * This method returns the current user form local storage.
     * @return Currentlysigned in user
     */
    val user: User?
        get() {
            val json = mPrefs.getString(USER_KEY, null)
            return if (json == null) null else mGson.fromJson(json, User::class.java)
        }

    /**
     * This method returns saved filters for pets.
     * @return Set of filters for available pets
     */
    val petFilters: Filters?
        get() {
            val json = mPrefs.getString(FILTERS_KEY, null)
            return if (json == null) null else mGson.fromJson(json, Filters::class.java)
        }

    /**
     * This method signs the current user out of local storage.
     */
    fun signOut() {
        mEditor.remove(USER_KEY)
        mEditor.commit()
    }
}