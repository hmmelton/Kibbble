package com.hmmelton.kibbble.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by harrison on 4/16/17.
 * This is a RecyclerView ItemDecoration used as spacing between the elements in a grid.
 */

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpacingSizePx;
    private int mNumCols;

    public GridSpaceItemDecoration(int spacingSizePx, int numCols) {
        mSpacingSizePx = spacingSizePx;
        mNumCols = numCols;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // Position of view in adapter
            int position = ((RecyclerView.LayoutParams) view.getLayoutParams())
                .getViewAdapterPosition();

        if (position / mNumCols == 0) {
            // Item is in first row
            outRect.top = mSpacingSizePx;
        }
        if (position % mNumCols == 0) {
            // Item is in first column
            outRect.left = mSpacingSizePx;
        }
        // Given to all items
        outRect.right = mSpacingSizePx;
        outRect.bottom = mSpacingSizePx;
    }
}
