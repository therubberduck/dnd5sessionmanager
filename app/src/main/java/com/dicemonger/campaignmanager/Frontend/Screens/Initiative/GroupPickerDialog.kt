package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dicemonger.campaignmanager.R

interface GroupPickerListener{
    fun groupAdded(combatant: CombatantDbo)
}

class GroupPickerDialog(_context: Activity, val _item: CombatantDbo, val _listener: GroupPickerListener) : AlertDialog(_context) {

    val _view : View

    val _edtNumber : EditText
    val _btnAdd : Button

    init {
        _view = _context.layoutInflater.inflate(R.layout.dialog_grouppicker, null)
        setView(_view)

        setCancelable(true)

        _edtNumber = _view.findViewById(R.id.edtNumber)
        _btnAdd = _view.findViewById(R.id.btnAdd)

        _btnAdd.setOnClickListener {addGroup() }

        show()
    }

    fun addGroup() {
        val number = _edtNumber.text

        if(!number.isBlank()){
            _item.groupNumber = number.toString().toInt()
            _listener.groupAdded(_item)
            cancel()
        }
    }
}