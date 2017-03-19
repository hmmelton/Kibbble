package com.hmmelton.kibbble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.hmmelton.kibbble.models.Profile;
import com.hmmelton.kibbble.utils.JsonUtil;
import com.hmmelton.kibbble.views.Card;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.scanner.ScanActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.swipeView)
    SwipePlaceHolderView mSwipeView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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

        // Create swipe view
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg_view));

        // Convert each JSON object to a Profile object, and display in swipe view
        for(Profile profile : JsonUtil.loadProfiles(this.getApplicationContext())){
            mSwipeView.addView(new Card(this, profile, mSwipeView));
        }

        setUpMyo();
    }

    private DeviceListener mListener = new AbstractDeviceListener() {
        @Override
        public void onConnect(Myo myo, long timestamp) {
            myo.unlock(Myo.UnlockType.HOLD); // Keep myo unlocked
            Toast.makeText(MainActivity.this, "Myo Connected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            Toast.makeText(MainActivity.this, "Myo Disconnected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            Log.d(TAG, "Pose: " + pose);
            // Read pose and respond accordingly
            switch (pose) {
                case WAVE_IN:
                    mSwipeView.doSwipe(false);
                    break;
                case WAVE_OUT:
                    mSwipeView.doSwipe(true);
                    break;
                default:
                    break;
            }
        }
    };

    private void setUpMyo() {
        // Get instance of hub, if there is one
        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
            Log.e(TAG, "Could not initialize the Hub.");
            return;
        }

        // Start Myo scan
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);

        // Set locking policy
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.STANDARD);

        Hub.getInstance().addListener(mListener); // Add listener
    }
}
