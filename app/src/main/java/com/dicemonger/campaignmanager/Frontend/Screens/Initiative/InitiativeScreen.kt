package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import com.wealthfront.magellan.Screen

class InitiativeScreen : Screen<InitiativeView>() {
    override fun createView(context: Context?): InitiativeView {
        val view = InitiativeView(context)

        return view
    }

}