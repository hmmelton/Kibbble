package com.hmmelton.kibbble.models

import com.google.gson.annotations.SerializedName

/**
 * Created by harrisonmelton on 4/30/17.
 * Model class for user
*/

class User(@SerializedName("first_name")
           var firstName: String?, @SerializedName("last_name")
           var lastName: String?, @SerializedName("email")
           var email: String?, @SerializedName("profile_url")
           var profileUrl: String?, @SerializedName("location")
           var location: String?)