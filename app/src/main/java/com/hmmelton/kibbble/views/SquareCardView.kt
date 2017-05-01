package com.hmmelton.kibbble.views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.hmmelton.kibbble.R

/**
 * Created by harrisonmelton on 4/30/17.
 * This is a custom CardView that is rendered as a square. The binding dimension (height or width)
 * is set in XML. If it is not set, the view's height is used to as the binding dimension.
 */
class SquareCardView : CardView {

    private var byHeight: Boolean = false


    constructor(context: Context) : super(context) {
        byHeight = true
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttributes(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        getAttributes(context, attrs, defStyleAttr, 0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (byHeight)
            super.onMeasure(heightMeasureSpec, heightMeasureSpec)
        else
            super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (byHeight)
            super.onSizeChanged(h, h, oldw, oldh)
        else
            super.onSizeChanged(w, w, oldw, oldh)
    }

    /**
     * This method fetches attributes passed in XML when the view is created
     * @param context context
     * @param attrs attributes given to the view in XML
     * @param defStyleAttr default style attribute
     * @param defStyleRes default style resource
     */
    private fun getAttributes(context: Context, attrs: AttributeSet, defStyleAttr: Int,
                              defStyleRes: Int) {
        // Grab custom attributes
        val array = context.theme
                .obtainStyledAttributes(attrs, R.styleable.SquareView, defStyleAttr,
                        defStyleRes)
        try {
            // If the byHeight attribute was set, pass it to the class property
            byHeight = array.getBoolean(R.styleable.SquareView_byHeight, true)
        } finally {
            array.recycle()
        }
    }
}