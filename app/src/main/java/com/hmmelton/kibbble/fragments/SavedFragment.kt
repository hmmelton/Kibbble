package com.hmmelton.kibbble.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.firebase.auth.FirebaseAuth
import com.hmmelton.kibbble.R
import com.hmmelton.kibbble.SplashscreenActivity
import com.hmmelton.kibbble.adapters.SavedDogsAdapter
import com.hmmelton.kibbble.models.Pet
import com.hmmelton.kibbble.utils.SharedPrefsUtil
import java.util.ArrayList

/**
 * Created by harrisonmelton on 4/30/17.
 * This is a fragment for the saved pets page.
 */
class SavedFragment : Fragment() {

    @BindView(R.id.saved_recycler)
    internal var mSavedRecycler: RecyclerView? = null

    @OnClick(R.id.bt_sign_out)
    internal fun onSignOutClicked() {
        // Log out from Firebase and local storage
        SharedPrefsUtil.signOut()
        FirebaseAuth.getInstance().signOut()
        // Navigate to splash screen
        startActivity(Intent(activity, SplashscreenActivity::class.java))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_saved, container, false)
        val refreshLayout = rootView.findViewById(R.id.sw_saved_items) as SwipeRefreshLayout

        ButterKnife.bind(this, rootView)

        setUpRecyclerView()
        // Get initial saved dogs
        getSavedDogs(refreshLayout)
        // Set refresh listener
        refreshLayout.setOnRefreshListener { getSavedDogs(refreshLayout) }
        return rootView
    }

    /**
     * This method gets the user's saved dogs.
     * @param layout layout being refreshed
     */
    private fun getSavedDogs(layout: SwipeRefreshLayout) {
        // TODO: fill this in
    }

    /**
     * This method sets up the layout's RecyclerView
     */
    private fun setUpRecyclerView() {
        // TODO: fill with saved pets
        mSavedRecycler!!.layoutManager = LinearLayoutManager(activity)
        mSavedRecycler!!.adapter = SavedDogsAdapter(ArrayList<Pet>())
    }

    /**
     * Singleton that creates and returns a new instance of the fragment.
     */
    companion object {
        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
    }

}