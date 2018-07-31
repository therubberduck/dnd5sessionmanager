package com.dicemonger.campaignmanager.Utility

import android.util.Log
import android.widget.PopupMenu
import com.dicemonger.campaignmanager.R

fun PopupMenu.showMenuWithIcons(menuId: Int) {
    this.inflate(menuId)

    try {
        val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
        fieldPopup.isAccessible = true
        val popup = fieldPopup.get(this)
        popup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java).invoke(popup, true)
    }
    catch (e: Exception){
        Log.e("PopupMenuExtensions", "Error showing menu icons", e)
    }
    finally {
        this.show()
    }
}