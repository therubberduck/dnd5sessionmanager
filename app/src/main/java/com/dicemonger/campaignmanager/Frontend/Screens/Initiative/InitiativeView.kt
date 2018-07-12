package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import android.view.View
import android.widget.ListView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.BaseScreenView

class InitiativeView(context: Context?) : BaseScreenView<InitiativeScreen>(context) {
    init {
        View.inflate(context, R.layout.vw_initiative, this)
    }

    fun setAdapter(items: List<Creature>, listener: ObjectListAdapterListener<Creature>) {
        val lstInitiative = findViewById<ListView>(R.id.lstInitiative)

        val adapter = InitiativeListAdapter(items, listener, lstInitiative)

        lstInitiative.adapter = adapter
    }
}