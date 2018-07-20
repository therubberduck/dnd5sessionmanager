package com.dicemonger.campaignmanager.Data

import android.content.Context
import com.dicemonger.campaignmanager.Database.AppDatabase
import com.dicemonger.campaignmanager.Model.Creature

class DataProvider() {

    private lateinit var _db: AppDatabase

    companion object {
        private val _instance = DataProvider()

        fun initialize(context: Context) {
            _instance._db = AppDatabase(context)
        }

        fun get() : DataProvider {
            return _instance
        }
    }

    fun deleteCharacter(id: Long) {
        _db.Profile.delete(id)
    }

    fun getCharacters(callback: (List<Creature>) -> Unit) {
        _db.Profile.getAll(callback)
    }

    fun getCharacter(id: Long, callback: (Creature) -> Unit) {
        _db.Profile.get(id, callback)
    }

    fun add(character: Creature, callback: ((Creature) -> Unit)?) {
        _db.Profile.add(character, callback)
    }

    fun update(character: Creature) {
        _db.Profile.update(character)
    }
}