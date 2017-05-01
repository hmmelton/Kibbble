package com.hmmelton.kibbble

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.hmmelton.kibbble.fragments.MainFragment
import com.hmmelton.kibbble.fragments.SavedFragment
import com.hmmelton.kibbble.views.NonSwipeableViewPager
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    @Suppress("unused")
    private val TAG = javaClass.simpleName
    private val mFilters: Bundle? = null

    @BindView(R.id.toolbar)
    internal var mToolbar: Toolbar? = null
    @BindView(R.id.pager)
    internal var mPager: NonSwipeableViewPager? = null

    /*
     * OnClick methods for scrolling tabs
     */
    @OnClick(R.id.toolbar_saved)
    internal fun onSavedClicked() {
        mPager!!.setCurrentItem(1, true)
    }

    @OnClick(R.id.toolbar_title)
    internal fun onTitleClick() {
        mPager!!.setCurrentItem(0, true)
    }

    @OnClick(R.id.toolbar_filters)
    internal fun onProfileClicked() {
        // TODO: bring up filters
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        // Remove content insets
        mToolbar!!.setContentInsetsAbsolute(0, 0)
        setSupportActionBar(mToolbar)
        // Remove default title
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        setUpViewPager()

        // Set pager to home item
        mPager?.currentItem = 0
    }

    /**
     * This method sets up the Activity's ViewPager
     */
    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // Add Fragments
        adapter.addFragment(MainFragment.newInstance())
        adapter.addFragment(SavedFragment.newInstance())
        mPager!!.adapter = adapter
    }

    // Adapter class for view pager tabs
    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }
    }
}