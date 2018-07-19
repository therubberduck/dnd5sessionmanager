package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Model.Creature
import com.wealthfront.magellan.Screen

class InitiativeScreen : Screen<InitiativeView>(), InitiativeListListener, InitPickerDialogListener {

    var currentCount = 0
    val charactersNotOnList = ArrayList<Creature>()

    override fun createView(context: Context?): InitiativeView {
        val view = InitiativeView(context)

        setupCharacters()

        view.setAdapter(this)
        view.setNextButton { nextInitiative() }
        view.setAddButton{addInitiative()}

        //Add all characters by default
        charactersNotOnList.forEach {
            it.rollInitiative()
            view.adapter.addItem(it)
        }
        charactersNotOnList.removeIf { true }
        view.adapter.sortByInt { -it.currentInit }

        return view
    }

    override fun getContext(): Context {
        return activity
    }

    //
    // UI Setup
    //

    fun setupCharacters() {
        val creatures = DataProvider.get().getCharacters()
        charactersNotOnList.addAll(creatures)
    }

    fun addInitiative() {
        InitPickerDialog(activity, this, charactersNotOnList)
    }

    fun nextInitiative() {
        var currentSelected = view.adapter.currentSelected + 1

        if(currentSelected >= view.adapter.itemCount) {
            currentSelected = 0
        }

        view.adapter.currentSelected = currentSelected

        //Unready any readied creature when it becomes that creature's turn
        view.adapter.getItem(currentSelected).isReadied = false
    }

    //
    // Init Picker
    //

    override fun creatureAdded(creature: Creature) {
        creature.rollInitiative()
        view.adapter.addItem(creature)
        view.adapter.sortByInt { -it.currentInit }

        val index = view.adapter.getPosition(creature)

        //Adjust if this changes the index of the selected item
        if(index <= view.adapter.currentSelected) {
            view.adapter.currentSelected += 1
        }

        //Remove from list of creatures that can be added
        charactersNotOnList.remove(creature)
    }

    //
    // Cell Handling
    //

    override fun itemClicked(item: Creature) {
        TODO("not implemented") //Currently itemClicked is not implemented or needed
    }

    override fun initReady(creature: Creature) {
        creature.apply { isReadied = !isReadied }
        view.adapter.notifyDataSetChanged()
    }

    override fun initRemove(creature: Creature) {
        val index = view.adapter.getPosition(creature)
        view.adapter.removeItem(creature)

        //Adjust if this changes the index of the selected item
        if(index < view.adapter.currentSelected) {
            view.adapter.currentSelected -= 1
        }

        //Add back on list of creatures that can be added
        charactersNotOnList.add(creature)
    }
}