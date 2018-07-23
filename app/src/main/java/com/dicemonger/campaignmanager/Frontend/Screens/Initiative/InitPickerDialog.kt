package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.app.Activity
import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

interface InitPickerDialogListener {
    fun creatureAdded(creature: Creature)
}

class InitPickerDialog(_context: Activity, private val _listener: InitPickerDialogListener, val creatures: ArrayList<Creature>) : AlertDialog(_context), ObjectListAdapterListener<Creature> {

    val _view : View

    val _edtName : EditText
    val _edtInit : EditText
    val _btnAdd : Button
    val _lstPicker : RecyclerView

    init {
        _view = _context.layoutInflater.inflate(R.layout.dialog_initpicker, null)
        setView(_view)

        setTitle(_context.getString(R.string.dialog_addcombatant))
        setCancelable(true)

        _edtName = _view.findViewById(R.id.edtName)
        _edtInit = _view.findViewById(R.id.edtInit)
        _btnAdd = _view.findViewById(R.id.btnAdd)
        _lstPicker = _view.findViewById<RecyclerView>(R.id.rclInitPicker)

        _btnAdd.setOnClickListener {addCustomCombatant() }

        val adapter = InitPickerAdapter(creatures, this, _lstPicker)
        _lstPicker.adapter = adapter

        show()
    }

    override fun itemClicked(item: Creature) {
        addCombatant(item)
    }

    fun addCustomCombatant() {
        val name = _edtName.text
        val init = _edtInit.text

        if(!name.isBlank() && !init.isBlank()){
            val creature = Creature(name.toString(), init.toString().toInt(), false)
            addCombatant(creature)
        }
    }

    fun addCombatant(combatant: Creature) {
        _listener.creatureAdded(combatant)
        cancel()
    }
}