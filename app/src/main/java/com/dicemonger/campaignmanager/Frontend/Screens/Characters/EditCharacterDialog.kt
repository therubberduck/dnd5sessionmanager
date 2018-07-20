package com.dicemonger.campaignmanager.Frontend.Screens.Characters

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

interface EditCharacterDialogListener {
    fun characterAdded(character: Creature)
    fun characterEdited(character: Creature)
}

class EditCharacterDialog(_context: Activity, _characterId: Int?, private val _listener: EditCharacterDialogListener) : AlertDialog(_context) {

    val _view : View

    val _edtName : EditText
    val _edtInit : EditText
    val _btnAdd : Button

    val _data = DataProvider.get()

    private val _isNewCharacter = _characterId == null

    init {
        _view = _context.layoutInflater.inflate(R.layout.dialog_initpicker, null)
        setView(_view)

        setTitle("Add Combatant")
        setCancelable(true)

        _edtName = _view.findViewById(R.id.edtName)
        _edtInit = _view.findViewById(R.id.edtInit)
        _btnAdd = _view.findViewById(R.id.btnAdd)

        if(_isNewCharacter) {
            _btnAdd.setText(R.string.global_add)
        }
        else {
            val character = _data.getCharacter(_characterId!!)
            _edtName.setText(character.name)
            _edtInit.setText(character.initBonus)
        }

        _btnAdd.setOnClickListener {submitCharacter() }

        show()
    }

    fun submitCharacter() {
        if(_isNewCharacter) {
            addCharacter()
        }
        else {
            editCharacter()
        }
    }

    fun addCharacter() {
        val name = _edtName.text
        val init = _edtInit.text

        if(!name.isBlank() && !init.isBlank()){
            val character = Creature(name.toString(), init.toString().toInt(), 0)
            DataProvider.get().add(character)
            _listener.characterAdded(character)
        }
    }

    fun editCharacter() {
        val name = _edtName.text
        val init = _edtInit.text

        if(!name.isBlank() && !init.isBlank()){
            val character = Creature(name.toString(), init.toString().toInt(), 0)
            DataProvider.get().update(character)
            _listener.characterEdited(character)
        }
    }
}