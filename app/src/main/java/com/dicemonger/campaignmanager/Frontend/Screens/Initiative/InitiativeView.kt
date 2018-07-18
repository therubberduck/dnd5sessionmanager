package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Frontend.Screens.RecyclerViewMargin
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.BaseScreenView

class InitiativeView(context: Context?) : BaseScreenView<InitiativeScreen>(context) {

    private val _rclInitiative : RecyclerView
    lateinit var adapter: InitiativeListAdapter

    init {
        View.inflate(context, R.layout.vw_initiative, this)

        _rclInitiative = findViewById<RecyclerView>(R.id.lstInitiative)
        _rclInitiative.layoutManager = LinearLayoutManager(context)
        _rclInitiative.addItemDecoration(RecyclerViewMargin(4, 1))
    }

    fun setAdapter(listener: InitiativeListListener) {

        adapter = InitiativeListAdapter(listOf(), listener, _rclInitiative)

        _rclInitiative.adapter = adapter
    }

    fun setAddButton(listener: (View) -> Unit) {
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener(listener)
    }
}