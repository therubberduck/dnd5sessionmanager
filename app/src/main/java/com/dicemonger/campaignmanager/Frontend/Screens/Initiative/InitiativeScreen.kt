package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Frontend.Screens.Initiative.Dialogs.*
import com.dicemonger.campaignmanager.R
import com.dicemonger.campaignmanager.Utility.showMenuWithIcons
import com.dicemonger.campaignmanager.Frontend.Screens.Initiative.ViewModel.CombatantDbo
import com.wealthfront.magellan.Screen

class InitiativeScreen : Screen<InitiativeView>(), InitiativeListListener, InitPickerDialogListener, GroupPickerListener, ConditionDialogListener {

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
    // Init Picker / Group Picker
    //

    override fun combatantAdded(item: CombatantDbo) {
        val listObject: CombatantDbo
        if(item.isMonster) {
            listObject = item.copy()
        }
        else {
            listObject = item
        }

        listObject.rollInitiative()
        view.adapter.addItem(listObject)
        view.adapter.sortByInt { -it.currentInit }

        val index = view.adapter.getPosition(listObject)

        //Adjust if this changes the index of the selected item
        if(index <= view.adapter.currentSelected && view.adapter.currentSelected + 1 < view.adapter.itemCount) {
            view.adapter.currentSelected += 1
        }

        adjustSelectedItemIndex(listObject)

        //Remove from list of creatures that can be added
        if(!item.isMonster) {
            item.canAddToList = false
        }

    }

    override fun groupStartAdding(combatant: CombatantDbo) {
        GroupPickerDialog(activity, combatant.copy(), this)
    }

    override fun groupAdded(combatant: CombatantDbo) {
        combatant.rollInitiative()
        view.adapter.addItem(combatant)
        view.adapter.sortByInt { -it.currentInit }

        adjustSelectedItemIndex(combatant)
    }

    fun adjustSelectedItemIndex(item: CombatantDbo) {
        val index = view.adapter.getPosition(item)

        if(index <= view.adapter.currentSelected) {
            view.adapter.currentSelected += 1
        }
    }

    //
    // Conditions
    //

    fun addCondition(combatant: CombatantDbo) {
        ConditionDialog(activity, combatant, this)
    }

    override fun conditionAdded(combatant: CombatantDbo) {
        combatant.conditions.sortBy { it.name }
        view.adapter.notifyItemChanged(combatant)
    }

    //
    // Cell Handling
    //

    override fun itemClicked(item: CombatantDbo, view: View) {
        val popupMenu = PopupMenu(activity, view)
        popupMenu.setOnMenuItemClickListener {
            menuItem ->
            when(menuItem.itemId){
                R.id.mn_condition -> {addCondition(item);true}
                R.id.mn_ready -> {initReady(item); true}
                else -> false
            }
        }

        popupMenu.showMenuWithIcons(R.menu.initiativeitem_action)
    }

    override fun initReady(combatant: CombatantDbo) {
        combatant.apply { isReadied = !isReadied }
        view.adapter.notifyDataSetChanged()
    }

    override fun initRemove(combatant: CombatantDbo) {
        //Remove single combatant from list
        if(combatant.groupNumber <= 1) {
            initRemoveAll(combatant)
        }
        //Decrease number of combatants
        else {
            combatant.groupNumber--
            view.adapter.notifyItemChanged(combatant)
        }
    }

    override fun initRemoveAll(combatant: CombatantDbo) {
        val index = view.adapter.getPosition(combatant)
        view.adapter.removeItem(combatant)

        //Adjust if this changes the index of the selected item
        if(index < view.adapter.currentSelected && index != 0) {
            view.adapter.currentSelected -= 1
        }

        //Add back on list of creatures that can be added
        combatant.canAddToList = true
    }
}