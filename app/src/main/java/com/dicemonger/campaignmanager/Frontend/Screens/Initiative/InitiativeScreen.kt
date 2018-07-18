package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
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
        val creatures = listOf(
                Creature("Hamelt", 2, 0),
                Creature("Grommund", 5, 0),
                Creature("Green", 1, 0),
                Creature("Maxim", 4, 0)
        )
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
    }

    //
    // Init Picker
    //

    override fun creatureAdded(creature: Creature) {
        creature.rollInitiative()
        view.adapter.addItem(creature)
        view.adapter.sortByInt { -it.currentInit }
        charactersNotOnList.remove(creature)
    }

    //
    // Cell Handling
    //

    override fun itemClicked(item: Creature) {
        TODO("not implemented") //Currently itemClicked is not implemented or needed
    }

    override fun initDelay(creature: Creature) {

    }

    override fun initRemove(creature: Creature) {
        view.adapter.removeItem(creature)
        charactersNotOnList.add(creature)
    }
}