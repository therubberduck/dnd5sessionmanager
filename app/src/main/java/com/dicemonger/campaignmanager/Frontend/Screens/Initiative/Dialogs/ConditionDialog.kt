package com.dicemonger.campaignmanager.Frontend.Screens.Initiative.Dialogs

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dicemonger.campaignmanager.R
import com.dicemonger.campaignmanager.Utility.children
import com.dicemonger.campaignmanager.ViewModel.CombatantDbo
import org.apmem.tools.layouts.FlowLayout

interface ConditionDialogListener{
    fun conditionAdded(combatant: CombatantDbo)
}

class ConditionDialog(_context: Activity, val _item: CombatantDbo, val listener: ConditionDialogListener) : AlertDialog(_context) {

    val _view : View

    val _edtConditions : EditText
    val _btnAdd : Button
    val _flwConditions : FlowLayout

    init {
        _view = _context.layoutInflater.inflate(R.layout.dialog_condition, null)
        setView(_view)

        setTitle(_item.name + "'s Conditions")
        setCancelable(true)

        _edtConditions = _view.findViewById(R.id.edtCondition)
        _btnAdd = _view.findViewById(R.id.btnAdd)
        _flwConditions = _view.findViewById(R.id.flwConditions)

        _btnAdd.setOnClickListener {addCondition(_edtConditions.text.toString()) }

        _flwConditions.children<Button> {
            btn ->
            btn.setOnClickListener { addCondition(btn.text.toString()) }
        }
        show()
    }

    fun addCondition(condition: String) {
        if(!condition.isEmpty()) {
            _item.conditions.add(condition)
        }
        listener.conditionAdded(_item)
        cancel()
    }
}