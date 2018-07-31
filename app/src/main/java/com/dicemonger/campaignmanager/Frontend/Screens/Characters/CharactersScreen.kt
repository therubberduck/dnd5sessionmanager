package com.dicemonger.campaignmanager.Frontend.Screens.Characters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Frontend.Screens.DeletionDialog
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Frontend.Screens.getString
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

    override fun itemClicked(item: Character, view: View) {
        EditCharacterDialog(activity, item.id, this)
    }

    //Delete character
    override fun itemLongClicked(item: Character, view: View) {
        dialog = DeletionDialog.show(
                getString(R.string.dialog_deletecharacter_title),
                getString(R.string.dialog_deletecharacter_body, item.name),
                activity)
        {
            deleteCharacter(item)
        }
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