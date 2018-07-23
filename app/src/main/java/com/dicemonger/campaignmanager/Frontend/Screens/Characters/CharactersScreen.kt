package com.dicemonger.campaignmanager.Frontend.Screens.Characters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Character
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.Screen

class CharactersScreen : Screen<CharactersView>(), ObjectListAdapterListener<Character>, EditCharacterDialogListener {

    var dialog: AlertDialog? = null

    override fun createView(context: Context): CharactersView {
        val view = CharactersView(context)

        DataProvider.get().getCharacters{
            characters ->
            view.setAdapter(this, characters)
            view.adapter.sortByString { it.name }
        }



        view.setAddButton { createNewCharacter() }

        return view
    }

    fun createNewCharacter() {
        EditCharacterDialog(activity, null, this)
    }

    //
    // ObjectListAdapterListener functions
    //

    override fun getContext(): Context {
        return activity
    }

    override fun itemClicked(item: Character) {
        EditCharacterDialog(activity, item.id, this)
    }

    //Delete character
    override fun itemLongClicked(item: Character) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(activity.getString(R.string.dialog_deletecharacter_body, item.name))
                .setTitle(R.string.dialog_deletecharacter_title)
                .setPositiveButton(R.string.global_delete, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, id: Int) {
                        deleteCharacter(item)
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

    fun deleteCharacter(character: Character) {
        view.adapter.removeItem(character)
        DataProvider.get().delete(character)
    }

    //
    // EditCharacterDialogListener functions
    //

    override fun characterAdded(character: Character) {
        view.adapter.addItem(character)
        view.adapter.sortByString { it.name }
    }

    override fun characterEdited(character: Character) {
        view.adapter.replaceItem(character){it.id == character.id}
        view.adapter.sortByString { it.name }
    }
}