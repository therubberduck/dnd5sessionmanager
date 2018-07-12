package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature

class InitiativeListAdapter(items: List<Creature>, listener: ObjectListAdapterListener<Creature>, listview: ListView) : ObjectListAdapter<Creature>(items, listener, listview) {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}