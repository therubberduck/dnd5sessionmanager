package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import com.dicemonger.campaignmanager.Data.DataProvider
import com.wealthfront.magellan.Screen

class InitiativeScreen : Screen<InitiativeView>(), InitiativeListListener, InitPickerDialogListener {

    val combatants = ArrayList<CombatantDbo>()

    override fun createView(context: Context?): InitiativeView {
        val view = InitiativeView(context)

        view.setAdapter(this)
        view.setNextButton { nextInitiative() }
        view.setAddButton{addInitiative()}

        //Add all characters by default
        DataProvider.get().getCreatures {
            it.forEach {
                creature ->
                val combatant = CombatantDbo.create(creature)
                combatant.rollInitiative()
                combatants.add(combatant)
                if(!combatant.isMonster) {
                    view.adapter.addItem(combatant)
                    combatant.canAddToList = false
                }
            }

            view.adapter.sortByInt { -it.currentInit }
        }

        return view
    }

    override fun getContext(): Context {
        return activity
    }

    //
    // UI Setup
    //

    fun addInitiative() {
        InitPickerDialog(activity, this, combatants)
    }

    fun nextInitiative() {
        if(view.adapter.itemCount == 0) {
            return
        }

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

    override fun combatantAdded(pickerObject: CombatantDbo) {
        val listObject: CombatantDbo
        if(pickerObject.isMonster) {
            listObject = pickerObject.copy()
        }
        else {
            listObject = pickerObject
        }

        listObject.rollInitiative()
        view.adapter.addItem(listObject)
        view.adapter.sortByInt { -it.currentInit }

        val index = view.adapter.getPosition(listObject)

        //Adjust if this changes the index of the selected item
        if(index <= view.adapter.currentSelected) {
            view.adapter.currentSelected += 1
        }

        //Remove from list of creatures that can be added
        if(!pickerObject.isMonster) {
            pickerObject.canAddToList = false
        }

    }

    //
    // Cell Handling
    //

    override fun itemClicked(item: CombatantDbo) {
        TODO("not implemented") //Currently itemClicked is not implemented or needed
    }

    override fun initReady(creature: CombatantDbo) {
        creature.apply { isReadied = !isReadied }
        view.adapter.notifyDataSetChanged()
    }

    override fun initRemove(combatant: CombatantDbo) {
        val index = view.adapter.getPosition(combatant)
        view.adapter.removeItem(combatant)

        //Adjust if this changes the index of the selected item
        if(index < view.adapter.currentSelected) {
            view.adapter.currentSelected -= 1
        }

        //Add back on list of creatures that can be added
        combatant.canAddToList = true
    }
}