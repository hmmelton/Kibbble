package com.hmmelton.kibbble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.settings_toolbar)
    Toolbar mToolbar;

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

    @OnClick(R.id.settings_back)
    void onBackClicked() {
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.apply_button)
    void onApplyClick() {
        Intent intent = new Intent();
        intent.putExtra("checkbox_male", mCheckBoxMale.isChecked());
        intent.putExtra("checkbox_female", mCheckBoxFemale.isChecked());
        intent.putExtra("checkbox_small", mCheckBoxSmall.isChecked());
        intent.putExtra("checkbox_medium", mCheckBoxMedium.isChecked());
        intent.putExtra("checkbox_large", mCheckBoxLarge.isChecked());

        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        // Remove content insets
        mToolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(mToolbar);
        // Remove default title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
