package com.hmmelton.kibbble.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hmmelton.kibbble.models.Profile;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harrisonmelton on 3/18/17.
 * This is a utility class for JSON encoding and decoding.
 */

public class JsonUtil {

    @SuppressWarnings("unused")
    private static final String TAG = "JsonUtil";

    /**
     * This method creates a list of profiles from a JSON file.
     * @param context Context used to load JSON file from assets
     * @return List of Profile objects taken from JSON file
     */
    public static List<Profile> loadProfiles(Context context){
        try {
            // Set up GSON
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            // Load JSON file from assets
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "profiles.json"));
            List<Profile> profileList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                // Convert each JSON object into a Profile object
                Profile profile = gson.fromJson(array.getString(i), Profile.class);
                profileList.add(profile);
            }

            return profileList;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method loads a JSON file from the application's assets.
     * @param context Context used to load JSON file from assets
     * @param jsonFileName Name of file to be loaded
     * @return Stringified version of JSON file
     */
    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets(); // Get assets

            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
