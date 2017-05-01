package com.hmmelton.kibbble.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.SeekBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hmmelton.kibbble.R

/**
 * Created by harrisonmelton on 4/30/17.
 * This is a fragment for the user's profile page.
 */
class FiltersFragment : Fragment() {

    @BindView(R.id.checkbox_male)
    internal var mCheckBoxMale: CheckBox? = null
    @BindView(R.id.checkbox_female)
    internal var mCheckBoxFemale: CheckBox? = null
    @BindView(R.id.checkbox_small)
    internal var mCheckBoxSmall: CheckBox? = null
    @BindView(R.id.checkbox_medium)
    internal var mCheckBoxMedium: CheckBox? = null
    @BindView(R.id.checkbox_large)
    internal var mCheckBoxLarge: CheckBox? = null
    @BindView(R.id.filters_seekbar)
    internal var mSeekBar: SeekBar? = null

    @OnClick(R.id.apply_button)
    internal fun onApplyClick() {
        val intent = Intent()
        intent.putExtra("checkbox_male", mCheckBoxMale?.isChecked)
        intent.putExtra("checkbox_female", mCheckBoxFemale?.isChecked)
        intent.putExtra("checkbox_small", mCheckBoxSmall?.isChecked)
        intent.putExtra("checkbox_medium", mCheckBoxMedium?.isChecked)
        intent.putExtra("checkbox_large", mCheckBoxLarge?.isChecked)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_filters, container, false)
        ButterKnife.bind(this, rootView)

        return rootView
    }

    /**
     * Singleton that creates and returns new instance of this fragment
     */
    companion object {
        fun newInstance(): FiltersFragment {
            return FiltersFragment()
        }
    }
}