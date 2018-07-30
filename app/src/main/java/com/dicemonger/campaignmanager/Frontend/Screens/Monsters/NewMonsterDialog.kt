package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.Model.Monster
import com.dicemonger.campaignmanager.R

interface NewMonsterDialogListener {
    fun monsterAdded(monster: Monster)
    fun monsterEdited(monster: Monster)
}

class NewMonsterDialog (_context: Activity, private val _monsterId: Long?, private val _listener: NewMonsterDialogListener) : AlertDialog(_context) {

    val _view : View

    val _edtName : EditText
    val _edtInit : EditText
    val _btnAdd : Button

    val _data = DataProvider.get()

    private val _isNewMonster = _monsterId == null

    init {
        _view = _context.layoutInflater.inflate(R.layout.dialog_newmonster, null)
        setView(_view)

        setCancelable(true)

        _edtName = _view.findViewById(R.id.edtName)
        _edtInit = _view.findViewById(R.id.edtInit)
        _btnAdd = _view.findViewById(R.id.btnAdd)

        if(_isNewMonster) {
            setTitle(_context.getString(R.string.dialog_createmonster))
        }
        else {
            setTitle(_context.getString(R.string.dialog_editmonster))
            _btnAdd.setText(R.string.global_edit)
            _data.getMonster(_monsterId!!) {
                monster ->
                _edtName.setText(monster.name)
                _edtInit.setText(monster.initBonus.toString())
            }
        }

        _btnAdd.setOnClickListener {submitMonster() }

        show()
    }

    fun submitMonster() {
        if(_isNewMonster) {
            createMonster()
        }
        else {
            editMonster()
        }
    }

    fun createMonster() {
        val name = _edtName.text
        val init = _edtInit.text

        if(!name.isBlank() && !init.isBlank()){
            val monsterDbo = Monster(-1, name.toString(), init.toString().toInt())
            _data.add(monsterDbo){
                newMonster ->
                _listener.monsterAdded(newMonster)
            }
            cancel()
        }
    }

    fun editMonster() {
        val name = _edtName.text
        val init = _edtInit.text

        if(!name.isBlank() && !init.isBlank()){
            val monster = Monster(_monsterId!!, name.toString(), init.toString().toInt())
            _data.update(monster)
            _listener.monsterEdited(monster)
            cancel()
        }
    }
}