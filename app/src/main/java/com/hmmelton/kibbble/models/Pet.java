package com.hmmelton.kibbble.models;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harrison on 4/15/17.
 * This is a model class representing a pet available for adoption.
 */

public class Pet implements Serializable {

    @PropertyName("id")
    String mId;
    @PropertyName("name")
    String mName;
    @PropertyName("breed")
    String mBreed;
    @PropertyName("age")
    int mAge;
    @PropertyName("saves")
    int mSaves;
    @PropertyName("gender")
    String mGender;
    @PropertyName("size")
    String mSize;
    @PropertyName("color")
    String mColor;
    @PropertyName("spayed_neutered")
    boolean mSpayedNeutered;
    @PropertyName("housetrained")
    boolean mHousetrained;
    @PropertyName("bio")
    String mBio;
    @PropertyName("images")
    List<String> mImages;
    @PropertyName("shelter_id")
    String mShelterId;

    // Empty constructor for Firebase
    public Pet() {}

    /* Getter methods */

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getBreed() {
        return mBreed;
    }

    public int getAge() {
        return mAge;
    }

    public int getSaves() {
        return mSaves;
    }

    public String getGender() {
        // TODO: change this in the future for localization
        return mGender.equals("male") ? "Male" : "Female";
    }

    public String getSize() {
        return mSize;
    }

    public String getColor() {
        return mColor;
    }

    public boolean isSpayedNeutered() {
        return mSpayedNeutered;
    }

    public boolean isHousetrained() {
        return mHousetrained;
    }

    public String getBio() {
        return mBio;
    }

    public List<String> getImages() {
        return mImages;
    }

    public String getShelterId() {
        return mShelterId;
    }
}
