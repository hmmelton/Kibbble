package com.hmmelton.kibbble.utils;

import android.content.Context;
import android.content.Intent;

import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.scanner.ScanActivity;

/**
 * Created by harrisonmelton on 3/18/17.
 * This is a utility class for Thalmic Myo integration.
 */

public class MyoUtil {

    private static DeviceListener mDeviceListener;

    public static void setUpMyo(Context context) {
        // Get instance of hub, if there is one
        Hub hub = Hub.getInstance();
        if (!hub.init(context)) {
            return;
        }

        // Start Myo scan
        Intent intent = new Intent(context, ScanActivity.class);
        context.startActivity(intent);

        // Set locking policy
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.STANDARD);

        Hub.getInstance().addListener(mDeviceListener); // Add listener
    }

    public static void setDeviceListener(DeviceListener listener) {
        mDeviceListener = listener;
    }
}
