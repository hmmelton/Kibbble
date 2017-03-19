package com.hmmelton.kibbble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hmmelton.kibbble.models.Profile;
import com.hmmelton.kibbble.views.SquareImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DogProfileActivity extends AppCompatActivity {

    @BindView(R.id.dog_profile_image)
    SquareImageView mProfileImage;
    @BindView(R.id.dog_profile_name)
    TextView mProfileName;
    @BindView(R.id.dog_profile_location)
    TextView mProfileLocation;
    @BindView(R.id.dog_profile_toolbar)
    Toolbar mToolbar;
    @OnClick(R.id.dog_profile_back)
    void onBackClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profile);

        ButterKnife.bind(this);
        // Get dog info from intent extras
        Profile dog = (Profile) getIntent().getSerializableExtra("profile");

        // Set insets to 0
        mToolbar.setContentInsetsAbsolute(0, 0);
        // Set toolbar as action bar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide default title

        // Display image
        Glide.with(this)
                .load(dog.getUrl())
                .into(mProfileImage);
        // Display name/age
        mProfileName.setText(String.format("%s, %s", dog.getName(), dog.getAge()));
        // Display location
        mProfileLocation.setText(String.format("%s", dog.getLocation()));
    }
}
