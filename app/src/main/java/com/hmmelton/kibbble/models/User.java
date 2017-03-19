package com.hmmelton.kibbble.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harrisonmelton on 3/18/17.
 * Model class for user
 */

public class User {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("profile_url")
    private String profileUrl;

    public User(String firstName, String lastName, String email, String profileUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileUrl = profileUrl;
    }

    /*
     * Getters and Setters
     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
