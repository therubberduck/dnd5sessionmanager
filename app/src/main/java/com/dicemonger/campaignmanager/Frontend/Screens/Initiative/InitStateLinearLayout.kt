package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.dicemonger.campaignmanager.R

class InitStateLinearLayout(context: Context?, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private val STATE_ACTIVE = intArrayOf(R.attr.state_active)
    private val STATE_READIED = intArrayOf(R.attr.state_readied)
    var isActive = false
        get
        set(value) {
            if(value != field) {
                field = value
                refreshDrawableState()
            }
        }
    var isReady = false
        get
        set(value) {
            if(value != field) {
                field = value
                refreshDrawableState()
            }
        }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 2)
        if(isActive) {
            View.mergeDrawableStates(drawableState, STATE_ACTIVE)
        }
        if(isReady) {
            View.mergeDrawableStates(drawableState, STATE_READIED)
        }
        return drawableState
    }
}