package com.hmmelton.kibbble.views

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by harrisonmelton on 4/30/17.
 * This is a RecyclerView ItemDecoration used as spacing between the elements in a grid.
 */
class GridSpaceItemDecoration(private val spacingSizePx: Int, private val numCols: Int) :
        RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        // Position of view in adapter
        val position = (view.layoutParams as RecyclerView.LayoutParams)
                .viewAdapterPosition

        if (position / numCols == 0) {
            // Item is in first row
            outRect.top = spacingSizePx
        }
        /*if (position % numCols == 0) {
            // Item is in first column
            outRect.left = spacingSizePx;
        }*/
        // Given to all items
        outRect.right = spacingSizePx
        outRect.bottom = spacingSizePx
    }
}