package com.hmmelton.kibbble.models;

/**
 * Created by harrison on 4/15/17.
 * This is a model class representing a pet available for adoption.
 */

public class Pet {

    private String mId;
    private String mName;
    private String mBreed;
    private int mAge;
    private String mGender;
    private String mSize;
    private String mColor;
    private boolean mSpayedNeutered;
    private boolean mHousetrained;
    private String mBio;
    private String[] mImages;
    private String mShelterId;

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

    public String getGender() {
        return mGender;
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

    public String[] getImages() {
        return mImages;
    }

    public String getShelterId() {
        return mShelterId;
    }
}
