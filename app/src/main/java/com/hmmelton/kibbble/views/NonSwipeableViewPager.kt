package com.hmmelton.kibbble.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller

/**
 * Created by harrisonmelton on 4/30/17.
 * This is a custom ViewPager that prevents swiping to scroll.
 */
class NonSwipeableViewPager : ViewPager {
    constructor(context: Context) : super(context) {
        setCustomScroller()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setCustomScroller()
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    private fun setCustomScroller() {
        try {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, CustomScroller(context))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Custom class that handles ViewPager scrolling functionality.
     */
    private inner class CustomScroller(context: Context) :
            Scroller(context, DecelerateInterpolator()) {
        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, 250 /*1 secs*/)
        }
    }
}