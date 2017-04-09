package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Profile;
import com.hmmelton.kibbble.utils.JsonUtil;
import com.hmmelton.kibbble.views.Card;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        mProfiles = new ArrayList<>();


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
