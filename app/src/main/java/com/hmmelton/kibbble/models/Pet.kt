package com.hmmelton.kibbble.models

import com.google.firebase.database.PropertyName
import java.io.Serializable

/**
 * Created by harrisonmelton on 4/30/17.
 * This is a data model for pets.
 */
// Empty constructor for Firebase
class Pet : Serializable {

    /* Getter methods */

    @PropertyName("id")
    var id: String? = null
        internal set
    @PropertyName("name")
    var name: String? = null
        internal set get
    @PropertyName("breed")
    var breed: String? = null
        internal set
    @PropertyName("age")
    var age: Int = 0
        internal set
    @PropertyName("saves")
    var saves: Int = 0
        internal set
    @PropertyName("gender")
    internal var mGender: String? = null
    @PropertyName("size")
    var size: String? = null
        internal set
    @PropertyName("color")
    var color: String? = null
        internal set
    @PropertyName("spayed_neutered")
    var isSpayedNeutered: Boolean = false
        internal set
    @PropertyName("housetrained")
    var isHousetrained: Boolean = false
        internal set
    @PropertyName("bio")
    var bio: String? = null
        internal set
    @PropertyName("images")
    var images: List<String>? = null
        internal set
    @PropertyName("shelter_id")
    var shelterId: String? = null
        internal set

    // TODO: change this in the future for localization
    val gender: String
        get() = if (mGender == "male") "Male" else "Female"
}