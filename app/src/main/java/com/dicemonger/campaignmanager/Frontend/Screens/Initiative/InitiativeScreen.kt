package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.wealthfront.magellan.Screen

class InitiativeScreen : Screen<InitiativeView>(), ObjectListAdapterListener<Creature> {

    var currentCount = 0
    val creatureList = ArrayList<Creature>()

    override fun createView(context: Context?): InitiativeView {
        val view = InitiativeView(context)

        view.setAdapter(this)
        view.setAddButton{addInitiative()}

        return view
    }

    fun addInitiative() {
//        if(currentCount%2 == 0) {
//            currentCount -= 3
//        }
//        else {
//            currentCount += 5
//        }
//        val creature = Creature("Goblin", 0, currentCount)
//        view.adapter.addItem(creature)
//        view.adapter.sortByInt { it.currentInit }
        InitPickerDialog(activity)
    }

    override fun getContext(): Context {
        return activity
    }

    override fun itemClicked(item: Creature) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}