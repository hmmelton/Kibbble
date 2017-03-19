package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.kibbble.R;

import butterknife.ButterKnife;

/**
 * Created by harrisonmelton on 3/18/17.
 * This is a fragment for the saved pets page.
 */

public class SavedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public static SavedFragment newInstance() {
        return new SavedFragment();
    }
}
