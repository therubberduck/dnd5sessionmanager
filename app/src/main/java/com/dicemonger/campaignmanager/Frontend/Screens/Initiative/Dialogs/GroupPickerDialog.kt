package com.dicemonger.campaignmanager.Frontend.Screens.Initiative.Dialogs

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import com.dicemonger.campaignmanager.R
import com.dicemonger.campaignmanager.Utility.children
import com.dicemonger.campaignmanager.ViewModel.CombatantDbo

interface GroupPickerListener{
    fun groupAdded(combatant: CombatantDbo)
}

class GroupPickerDialog(_context: Activity, val _item: CombatantDbo, val _listener: GroupPickerListener) : AlertDialog(_context) {

    val _view : View

    val _edtNumber : EditText
    val _edtTag : EditText
    val _btnAdd : Button

    val _grdNumbers : GridLayout

    init {
        _view = _context.layoutInflater.inflate(R.layout.dialog_grouppicker, null)
        setView(_view)

        setTitle(_item.name + "s")
        setCancelable(true)

        _edtNumber = _view.findViewById(R.id.edtNumber)
        _edtTag = _view.findViewById(R.id.edtTag)
        _btnAdd = _view.findViewById(R.id.btnAdd)
        _grdNumbers = _view.findViewById(R.id.grdNumbers)

        _btnAdd.setOnClickListener {addGroup() }

        _grdNumbers.children<Button> {
            btn -> btn.setOnClickListener { clickedNumberButton(btn) }
        }

        show()
    }

    fun addGroup() {
        val number = _edtNumber.text
        val tag = _edtTag.text

        if(!number.isBlank()){
            _item.tag = tag.toString()
            _item.groupNumber = number.toString().toInt()
            _listener.groupAdded(_item)
            cancel()
        }
    }

    private fun clickedNumberButton(button: Button) {
        val text = button.text
        val tag = _edtTag.text

        _item.tag = tag.toString()

        _item.groupNumber = text.toString().toInt()
        _listener.groupAdded(_item)
        cancel()

    }
}