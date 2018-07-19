package com.dicemonger.campaignmanager.Frontend.Screens.Characters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.BaseScreenView

class CharactersView(context: Context) : BaseScreenView<CharactersScreen>(context) {

    lateinit var adapter: CharactersListAdapter
    lateinit var list: RecyclerView

    init {
        View.inflate(context, R.layout.vw_characters, this)

        list = findViewById(R.id.lstCharacters)
    }

    fun setAdapter(listener: ObjectListAdapterListener<Creature>) {
        adapter = CharactersListAdapter(listOf(), listener, list)
        list.adapter = adapter
    }
}