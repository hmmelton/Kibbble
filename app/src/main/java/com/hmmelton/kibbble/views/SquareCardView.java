package com.hmmelton.kibbble.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.hmmelton.kibbble.R;

/**
 * Created by harrison on 4/16/17.
 * This is a custom CardView that is rendered as a square. The binding dimension (height or width)
 * is set in XML. If it is not set, the view's height is used to as the binding dimension.
 */

public class SquareCardView extends CardView {

    private boolean byHeight;


    public SquareCardView(Context context) {
        super(context);
        byHeight = true;
    }

    public SquareCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(context, attrs, 0, 0);
    }

    public SquareCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(context, attrs, defStyleAttr, 0);
    }

    @SuppressWarnings("all")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (byHeight)
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        else
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (byHeight)
            super.onSizeChanged(h, h, oldw, oldh);
        else
            super.onSizeChanged(w, w, oldw, oldh);
    }

    /**
     * This method fetches attributes passed in XML when the view is created
     * @param context context
     * @param attrs attributes given to the view in XML
     * @param defStyleAttr default style attribute
     * @param defStyleRes default style resource
     */
    private void getAttributes(Context context, AttributeSet attrs, int defStyleAttr,
                               int defStyleRes) {
        // Grab custom attributes
        TypedArray array = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.SquareView, defStyleAttr,
                        defStyleRes);
        try {
            // If the byHeight attribute was set, pass it to the class property
            byHeight = array.getBoolean(R.styleable.SquareView_byHeight, true);
        } finally {
            array.recycle();
        }
    }
}
