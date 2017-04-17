package com.hmmelton.kibbble.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.hmmelton.kibbble.R;

/**
 * Created by harrisonmelton on 3/19/17.
 * This is a custom ImageView that is rendered as a square. The binding dimension (height or width)
 * is set in XML. If it is not set, the view's height is used to as the binding dimension.
 */

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {

    private boolean byHeight;

    public SquareImageView(Context context) {
        super(context);
        byHeight = true;
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(context, attrs, 0, 0);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
