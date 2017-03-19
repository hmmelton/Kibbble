package com.hmmelton.kibbble;

import android.app.Application;

/**
 * Created by harrisonmelton on 3/18/17.
 * Application class
 */

public class KibbbleApplication extends Application {

    private static KibbbleApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    /**
     * This method returns a global instance of the application file to use as context.
     * @return global context
     */
    public static KibbbleApplication getInstance() {
        return instance;
    }
}
