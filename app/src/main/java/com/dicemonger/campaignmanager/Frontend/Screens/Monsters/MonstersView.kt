package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Frontend.ViewComponents.RecyclerViewMargin
import com.dicemonger.campaignmanager.Model.Monster
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.BaseScreenView

class MonstersView(context: Context) : BaseScreenView<MonstersScreen>(context) {
    private val _list: RecyclerView

    lateinit var adapter: MonstersListAdapter

    init {
        View.inflate(context, R.layout.vw_characters, this)

        _list = findViewById(R.id.lstCharacters)
        _list.addItemDecoration(RecyclerViewMargin(8, 1))
    }

    fun setAdapter(listener: ObjectListAdapterListener<Monster>, characters: List<Monster>) {
        adapter = MonstersListAdapter(characters, listener, _list)
        _list.adapter = adapter
    }

    fun setAddButton(listener: (View) -> Unit) {
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener(listener)
    }
}