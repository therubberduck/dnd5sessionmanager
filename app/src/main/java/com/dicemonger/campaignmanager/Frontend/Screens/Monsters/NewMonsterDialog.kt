package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

interface NewMonsterDialogListener {
    fun monsterAdded(character: Creature)
}

class NewMonsterDialog (_context: Activity, private val _listener: NewMonsterDialogListener) : AlertDialog(_context) {

    val _view : View

    val _edtName : EditText
    val _edtInit : EditText
    val _btnAdd : Button

    val _data = DataProvider.get()

    init {
        _view = _context.layoutInflater.inflate(R.layout.dialog_newmonster, null)
        setView(_view)

        setCancelable(true)

        _edtName = _view.findViewById(R.id.edtName)
        _edtInit = _view.findViewById(R.id.edtInit)
        _btnAdd = _view.findViewById(R.id.btnAdd)

        setTitle(_context.getString(R.string.dialog_createmonster))

        _btnAdd.setOnClickListener {createMonster() }

        show()
    }

    fun createMonster() {
        val name = _edtName.text
        val init = _edtInit.text

        if(!name.isBlank() && !init.isBlank()){
            val monsterDbo = Creature(name.toString(), init.toString().toInt(), true)
            _data.add(monsterDbo){
                newMonster ->
                _listener.monsterAdded(newMonster)
            }
            cancel()
        }
    }
}