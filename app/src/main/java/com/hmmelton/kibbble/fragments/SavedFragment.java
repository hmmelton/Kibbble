package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.adapters.SavedDogsAdapter;
import com.hmmelton.kibbble.models.Profile;
import com.hmmelton.kibbble.utils.DummyDataUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by harrisonmelton on 3/18/17.
 * This is a fragment for the saved pets page.
 */

public class SavedFragment extends Fragment {

    @BindView(R.id.saved_recycler)
    RecyclerView mSavedRecycler;

    @OnClick(R.id.bt_sign_out)
    void onSignOutClicked() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SwipeRefreshLayout rootView =
                (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_saved, container, false);

        ButterKnife.bind(this, rootView);

        setUpRecyclerView();
        // Get initial saved dogs
        getSavedDogs(rootView);
        // Set refresh listener
        rootView.setOnRefreshListener(() -> getSavedDogs(rootView));
        return rootView;
    }

    public static SavedFragment newInstance() {
        return new SavedFragment();
    }

    /**
     * This method gets the user's saved dogs.
     * @param layout layout being refreshed
     */
    private void getSavedDogs(SwipeRefreshLayout layout) {
        List<Profile> dogs = DummyDataUtil.getSavedProfiles();

        layout.setRefreshing(false);

        ((SavedDogsAdapter) mSavedRecycler.getAdapter()).updateList(dogs);
        mSavedRecycler.getAdapter().notifyDataSetChanged();
    }

    /**
     * This method sets up the layout's RecyclerView
     */
    private void setUpRecyclerView() {
        mSavedRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSavedRecycler.setAdapter(new SavedDogsAdapter(DummyDataUtil.getSavedProfiles()));
    }

}
