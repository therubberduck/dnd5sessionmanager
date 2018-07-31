package com.dicemonger.campaignmanager.Frontend.Screens.Main

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Frontend.ViewComponents.RecyclerViewMargin
import com.dicemonger.campaignmanager.Model.ScreenItem
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.BaseScreenView

class MainView(context: Context?) : BaseScreenView<MainScreen>(context) {

    private val _rclMain : RecyclerView

    init {
        View.inflate(context, R.layout.vw_main, this)

        _rclMain = findViewById<RecyclerView>(R.id.rclMain)
        _rclMain.layoutManager = GridLayoutManager(context, 2)
        _rclMain.addItemDecoration(RecyclerViewMargin(8, 2))
    }

    fun setAdapter(screens: List<ScreenItem>, listener: ObjectListAdapterListener<ScreenItem>) {
        val adapter = MainGridAdapter(screens, listener, _rclMain)

        _rclMain.adapter = adapter
    }
}