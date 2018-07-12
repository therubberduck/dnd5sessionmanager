package com.dicemonger.campaignmanager.Frontend.Screens

import android.content.res.Resources
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class RecyclerViewMargin(private val dpMargins: Int, private val columns: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        val position = parent?.getChildLayoutPosition(view)
        if(position != null && outRect != null) {
            val pxMargin = (dpMargins * ( Resources.getSystem().displayMetrics.densityDpi / 160f)).toInt()

            outRect.right = pxMargin
            outRect.bottom = pxMargin
            if(position < pxMargin) {
                outRect.top = pxMargin
            }
            if(position % columns == 0) {
                outRect.left = pxMargin
            }
        }
    }
}
