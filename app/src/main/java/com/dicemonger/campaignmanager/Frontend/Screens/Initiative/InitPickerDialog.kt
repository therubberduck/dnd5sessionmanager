package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.app.Activity
import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

class InitPickerDialog(val context: Activity) : AlertDialog(context), ObjectListAdapterListener<Creature> {

    val _view : View

    init {
        _view = context.layoutInflater.inflate(R.layout.dialog_initpicker, null)
        setView(_view)

        setTitle("Add Combatant")
        setCancelable(true)

        setupList()

        show()
    }

    fun setupList(){
        val creatures = listOf(
                Creature("Hamelt", 2, 0),
                Creature("Grommund", 5, 0),
                Creature("Green", 1, 0),
                Creature("Maxim", 4, 0)
        )

        val list = _view.findViewById<RecyclerView>(R.id.rclInitPicker)

        val adapter = InitPickerAdapter(creatures, this, list)
        list.adapter = adapter
    }

    override fun itemClicked(item: Creature) {

    }
}