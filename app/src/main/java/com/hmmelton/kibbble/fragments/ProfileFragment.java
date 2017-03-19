package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.User;
import com.hmmelton.kibbble.utils.SharedPrefsUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by harrisonmelton on 3/18/17.
 * This is a fragment for the user's profile page.
 */

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.profile_name)
    TextView mProfileName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, rootView);

        // Get current user
        User user = SharedPrefsUtil.getUser();
        // Check if user is null
        if (user != null) {
            // Load profile image
            Glide.with(getContext())
                    .load(user.getProfileUrl())
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .into(mProfileImage);

            // Display user's name
            mProfileName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        }

        return rootView;
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
}
