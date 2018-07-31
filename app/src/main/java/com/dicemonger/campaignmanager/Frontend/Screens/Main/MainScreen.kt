package com.dicemonger.campaignmanager.Frontend.Screens.Main

import android.content.Context
import android.view.View
import com.dicemonger.campaignmanager.Frontend.MainActivity
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.ScreenItem
import com.wealthfront.magellan.Screen

class MainScreen : Screen<MainView>(), ObjectListAdapterListener<ScreenItem> {

    override fun createView(context: Context?): MainView {
        val view = MainView(context)

        val screens = ScreenItem.getAll()
        view.setAdapter(screens, this)

        return view
    }

    override fun getContext(): Context {
        return activity
    }

    override fun itemClicked(item: ScreenItem, view: View) {
        val screen = item.getScreenObject()
        (activity as MainActivity).navigateTo(screen)
    }
}