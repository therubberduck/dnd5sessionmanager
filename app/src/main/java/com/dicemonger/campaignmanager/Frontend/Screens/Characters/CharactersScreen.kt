package com.dicemonger.campaignmanager.Frontend.Screens.Characters

import android.content.Context
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.wealthfront.magellan.Screen

class CharactersScreen : Screen<CharactersView>(), ObjectListAdapterListener<Creature> {

    override fun createView(context: Context): CharactersView {
        val view = CharactersView(context)

        val characters = DataProvider.get().getCharacters()

        view.setAdapter(this)
        view.adapter.sortByString { it.name }

        return view
    }

    override fun getContext(): Context {
        return activity
    }

    override fun itemClicked(item: Creature) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}