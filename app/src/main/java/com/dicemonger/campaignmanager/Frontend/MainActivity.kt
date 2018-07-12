package com.dicemonger.campaignmanager.Frontend

import android.os.Bundle
import com.dicemonger.campaignmanager.Frontend.Screens.Main.MainScreen
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.NavigationType
import com.wealthfront.magellan.Navigator
import com.wealthfront.magellan.Screen
import com.wealthfront.magellan.support.SingleActivity

class MainActivity : SingleActivity() {

    override fun createNavigator(): Navigator {
        return Navigator.withRoot(MainScreen()).build()
    }

    fun navigateTo(screen : Screen<*>) {
        getNavigator().show(screen)
    }

    fun navigatePop() {
        getNavigator().goBackToRoot(NavigationType.GO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }
}