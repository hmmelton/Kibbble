package com.hmmelton.kibbble

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.hmmelton.kibbble.models.Pet
import com.hmmelton.kibbble.views.SquareImageView

class DogProfileActivity : AppCompatActivity() {

    @BindView(R.id.dog_profile_image)
    internal var mProfileImage: SquareImageView? = null
    @BindView(R.id.dog_profile_name)
    internal var mProfileName: TextView? = null
    @BindView(R.id.dog_profile_gender)
    internal var mProfileGender: TextView? = null
    @BindView(R.id.dog_profile_toolbar)
    internal var mToolbar: Toolbar? = null

    @OnClick(R.id.dog_profile_back)
    internal fun onBackClicked() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_profile)

        ButterKnife.bind(this)
        // Get dog info from intent extras
        val pet = intent.getSerializableExtra("profile") as Pet

        // Set insets to 0
        mToolbar!!.setContentInsetsAbsolute(0, 0)
        // Set toolbar as action bar
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // hide default title

        // Display image
        Glide.with(this)
                .load(pet.images?.get(0))
                .into(mProfileImage!!)
        // Display name/age
        mProfileName?.text = pet.name + ", " + pet.age
        // Display location
        mProfileGender?.text = pet.gender
    }
}
