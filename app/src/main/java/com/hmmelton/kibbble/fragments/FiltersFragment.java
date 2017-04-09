package com.hmmelton.kibbble.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;

import com.hmmelton.kibbble.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by harrisonmelton on 3/18/17.
 * This is a fragment for the user's profile page.
 */

public class FiltersFragment extends Fragment {

    @BindView(R.id.checkbox_male)
    CheckBox mCheckBoxMale;
    @BindView(R.id.checkbox_female)
    CheckBox mCheckBoxFemale;
    @BindView(R.id.checkbox_small)
    CheckBox mCheckBoxSmall;
    @BindView(R.id.checkbox_medium)
    CheckBox mCheckBoxMedium;
    @BindView(R.id.checkbox_large)
    CheckBox mCheckBoxLarge;
    @BindView(R.id.filters_seekbar)
    SeekBar mSeekBar;

    @OnClick(R.id.apply_button)
    void onApplyClick() {
        Intent intent = new Intent();
        intent.putExtra("checkbox_male", mCheckBoxMale.isChecked());
        intent.putExtra("checkbox_female", mCheckBoxFemale.isChecked());
        intent.putExtra("checkbox_small", mCheckBoxSmall.isChecked());
        intent.putExtra("checkbox_medium", mCheckBoxMedium.isChecked());
        intent.putExtra("checkbox_large", mCheckBoxLarge.isChecked());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filters, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    public static FiltersFragment newInstance() {
        return new FiltersFragment();
    }
}
