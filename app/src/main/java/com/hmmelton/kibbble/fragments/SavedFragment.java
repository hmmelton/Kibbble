package com.hmmelton.kibbble.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.SplashscreenActivity;
import com.hmmelton.kibbble.adapters.SavedDogsAdapter;
import com.hmmelton.kibbble.utils.SharedPrefsUtil;

import java.util.ArrayList;

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
        // Log out from Firebase and local storage
        SharedPrefsUtil.signOut();
        FirebaseAuth.getInstance().signOut();
        // Navigate to splash screen
        startActivity(new Intent(getActivity(), SplashscreenActivity.class));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);
        SwipeRefreshLayout refreshLayout =
                (SwipeRefreshLayout) rootView.findViewById(R.id.sw_saved_items);

        ButterKnife.bind(this, rootView);

        setUpRecyclerView();
        // Get initial saved dogs
        getSavedDogs(refreshLayout);
        // Set refresh listener
        refreshLayout.setOnRefreshListener(() -> getSavedDogs(refreshLayout));
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
        // TODO: fill this in
    }

    /**
     * This method sets up the layout's RecyclerView
     */
    private void setUpRecyclerView() {
        // TODO: fill with saved pets
        mSavedRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSavedRecycler.setAdapter(new SavedDogsAdapter(new ArrayList<>()));
    }

}
