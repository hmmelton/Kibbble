package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Profile;
import com.hmmelton.kibbble.utils.JsonUtil;
import com.hmmelton.kibbble.utils.MyoUtil;
import com.hmmelton.kibbble.views.Card;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisonmelton on 3/18/17.
 * Fragment for the main page
 */

public class MainFragment extends Fragment {

    private List<Profile> mProfiles;

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.swipe_view)
    SwipePlaceHolderView mSwipeView;

    // Listener for Thalmic Myo
    private DeviceListener mListener = new AbstractDeviceListener() {
        @Override
        public void onConnect(Myo myo, long timestamp) {
            myo.unlock(Myo.UnlockType.HOLD); // Keep myo unlocked
            Toast.makeText(getActivity(), "Myo Connected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            Toast.makeText(getActivity(), "Myo Disconnected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            Log.d(TAG, "Pose: " + pose);
            // Read pose and respond accordingly
            switch (pose) {
                case WAVE_IN:
                    mSwipeView.doSwipe(false);
                    break;
                case WAVE_OUT:
                    mSwipeView.doSwipe(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        mProfiles = new ArrayList<>();

        MyoUtil.setDeviceListener(mListener);

        // Create swipe view
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg_view));

        // Convert each JSON object to a Profile object, and display in swipe view
        for(Profile profile : JsonUtil.loadProfiles(getContext())) {
            mSwipeView.addView(new Card(getActivity(), profile, mSwipeView));
            mProfiles.add(profile);
        }

        return rootView;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
