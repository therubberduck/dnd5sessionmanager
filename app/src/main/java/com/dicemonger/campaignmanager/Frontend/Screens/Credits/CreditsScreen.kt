package com.dicemonger.campaignmanager.Frontend.Screens.Credits

import android.content.Context
import com.wealthfront.magellan.Screen

class CreditsScreen : Screen<CreditsView>() {
    override fun createView(context: Context?): CreditsView {
        return CreditsView(context)
    }

}