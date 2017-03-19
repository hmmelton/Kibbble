package com.hmmelton.kibbble;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hmmelton.kibbble.fragments.MainFragment;
import com.hmmelton.kibbble.fragments.ProfileFragment;
import com.hmmelton.kibbble.fragments.SavedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.pager)
    ViewPager mPager;

    /*
     * OnClick methods for scrolling tabs
     */
    @OnClick(R.id.toolbar_profile)
    void onProfileClicked() {
        Log.d(TAG, "profile clicked");
        mPager.setCurrentItem(0, true);
    }

    @OnClick(R.id.toolbar_title)
    void onTitleClick() {
        mPager.setCurrentItem(1, true);
    }

    @OnClick(R.id.toolbar_saved)
    void onSavedClicked() {
        mPager.setCurrentItem(2, true);
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

        // Set pager to middle item
        mPager.setCurrentItem(1);
    }

    private void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add Fragments
        adapter.addFragment(ProfileFragment.newInstance());
        adapter.addFragment(MainFragment.newInstance());
        adapter.addFragment(SavedFragment.newInstance());
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
