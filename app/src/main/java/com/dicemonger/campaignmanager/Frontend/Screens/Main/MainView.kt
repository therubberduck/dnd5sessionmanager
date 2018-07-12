package com.dicemonger.campaignmanager.Frontend.Screens.Main

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.ScreenItem
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.BaseScreenView

class MainView(context: Context?) : BaseScreenView<MainScreen>(context) {

    init {
        View.inflate(context, R.layout.vw_main, this)
    }

    fun setAdapter(screens: List<ScreenItem>, listener: ObjectListAdapterListener<ScreenItem>) {
        val grdMain = findViewById<GridView>(R.id.grdMain)
        val adapter = MainGridAdapter(screens, listener, grdMain)

        grdMain.adapter = adapter
    }
}