package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.app.Activity
import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dicemonger.campaignmanager.R
import com.dicemonger.campaignmanager.ViewModel.CombatantDbo

interface InitPickerDialogListener {
    fun combatantAdded(combatant: CombatantDbo)
    fun groupStartAdding(combatant: CombatantDbo)
}

class InitPickerDialog(_context: Activity, private val _listener: InitPickerDialogListener, val creatures: ArrayList<CombatantDbo>) : AlertDialog(_context), InitPickerAdapterListener {

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

    override fun itemClicked(item: CombatantDbo, view: View) {
        addCombatant(item)
    }

    fun addCustomCombatant() {
        val name = _edtName.text
        val init = _edtInit.text

        if(!name.isBlank() && !init.isBlank()){
            val combatant = CombatantDbo.create(name.toString(), init.toString().toInt())
            addCombatant(combatant)
        }
    }

    fun addCombatant(combatant: CombatantDbo) {
        _listener.combatantAdded(combatant)
        cancel()
    }

    override fun groupAdded(combatant: CombatantDbo) {
        _listener.groupStartAdding(combatant)
        cancel()
    }
}