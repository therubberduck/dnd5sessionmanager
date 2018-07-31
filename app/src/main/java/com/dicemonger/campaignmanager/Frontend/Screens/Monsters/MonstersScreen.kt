package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Frontend.Dialogs.DeletionDialog
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Utility.getString
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
        NewMonsterDialog(activity, null, this)
    }

    //
    // ObjectListAdapterListener functions
    //

    override fun getContext(): Context {
        return activity
    }

    override fun itemClicked(item: Monster, view: View) {
        NewMonsterDialog(activity, item.id, this)
    }

    //Delete character
    override fun itemLongClicked(item: Monster, view: View) {
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

    override fun monsterEdited(monster: Monster) {
        view.adapter.replaceItem(monster){it.id == monster.id}
        view.adapter.sortByString { it.name }
    }
}