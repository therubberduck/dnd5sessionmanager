package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Frontend.Screens.DeletionDialog
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Frontend.Screens.getString
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.Model.Monster
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.Screen

class MonstersScreen : Screen<MonstersView>(), ObjectListAdapterListener<Monster>, NewMonsterDialogListener {

    var dialog: AlertDialog? = null

    override fun createView(context: Context): MonstersView {
        val view = MonstersView(context)

        DataProvider.get().getMonsters{
            monsters ->
            view.setAdapter(this, monsters)
            view.adapter.sortByString { it.name }
        }

        view.setAddButton { createNewMonster() }

        return view
    }

    fun createNewMonster() {
        NewMonsterDialog(activity, this)
    }

    //
    // ObjectListAdapterListener functions
    //

    override fun getContext(): Context {
        return activity
    }

    override fun itemClicked(item: Monster) {
        TODO("Edit Monster")
    }

    //Delete character
    override fun itemLongClicked(item: Monster) {
        dialog = DeletionDialog.show(
                getString(R.string.dialog_deletemonster_title),
                getString(R.string.dialog_deletemonster_body, item.name),
                activity)
        {
            deleteMonster(item)
        }
    }

    fun deleteMonster(monster: Monster) {
        view.adapter.removeItem(monster)
        DataProvider.get().delete(monster)
    }

    //
    // NewMonsterDialogListener functions
    //

    override fun monsterAdded(monster: Monster) {
        view.adapter.addItem(monster)
        view.adapter.sortByString { it.name }
    }
}