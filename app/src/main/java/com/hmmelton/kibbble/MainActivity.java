package com.hmmelton.kibbble;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hmmelton.kibbble.fragments.MainFragment;
import com.hmmelton.kibbble.fragments.SavedFragment;
import com.hmmelton.kibbble.views.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();
    private Bundle mFilters;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.pager)
    NonSwipeableViewPager mPager;

    /*
     * OnClick methods for scrolling tabs
     */
    @OnClick(R.id.toolbar_saved)
    void onSavedClicked() {
        mPager.setCurrentItem(1, true);
    }

    @OnClick(R.id.toolbar_title)
    void onTitleClick() {
        mPager.setCurrentItem(0, true);
    }

    @OnClick(R.id.toolbar_filters)
    void onProfileClicked() {
        // TODO: bring up filters
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Remove content insets
        mToolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(mToolbar);
        // Remove default title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setUpViewPager();

        // Set pager to home item
        mPager.setCurrentItem(0);
    }

    /**
     * This method sets up the Activity's ViewPager
     */
    private void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add Fragments
        adapter.addFragment(MainFragment.Companion.newInstance());
        adapter.addFragment(SavedFragment.Companion.newInstance());
        mPager.setAdapter(adapter);
    }

    // Adapter class for view pager tabs
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}
