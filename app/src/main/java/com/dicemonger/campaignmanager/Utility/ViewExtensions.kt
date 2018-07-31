package com.dicemonger.campaignmanager.Utility

import android.view.View
import android.view.ViewGroup

inline fun <reified T:View>ViewGroup.children(callback: (view: T) -> Unit) {
    for(i in 0..this.childCount) {
        val view = this.getChildAt(i)
        if(view is T) {
            callback(view as T)
        }
    }
}

