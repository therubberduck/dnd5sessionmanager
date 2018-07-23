package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
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
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(activity.getString(R.string.dialog_deletemonster_body, item.name))
                .setTitle(R.string.dialog_deletemonster_title)
                .setPositiveButton(R.string.global_delete, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        deleteMonster(item)
                    }
                })
                .setNegativeButton(R.string.global_cancel, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        dialog?.dismiss()
                    }
                })

        dialog = builder.create()
        dialog?.show()
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