package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.kibbble.R;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisonmelton on 3/18/17.
 * Fragment for the main page
 */

public class MainFragment extends Fragment {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.swipe_view)
    SwipePlaceHolderView mSwipeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        // Create swipe view
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg_view));

        // TODO: Add pets to view

        return rootView;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
