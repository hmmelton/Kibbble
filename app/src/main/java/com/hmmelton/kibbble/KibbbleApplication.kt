package com.hmmelton.kibbble

import android.app.Application

class KibbbleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    /**
     * Global instance of application file to use as context.
     */
    companion object {
        var instance: KibbbleApplication? = null
            private set
    }
}