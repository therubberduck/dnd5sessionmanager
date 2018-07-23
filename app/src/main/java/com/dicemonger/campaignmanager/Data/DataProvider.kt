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

    fun add(creature: Creature, callback: ((Creature) -> Unit)?) {
        if(creature.isMonster) {
            _db.Monsters.add(creature, callback)
        }
        else {
            _db.Characters.add(creature, callback)
        }
    }

    fun delete(creature: Creature) {
        if(creature.isMonster) {
            _db.Monsters.delete(creature.id!!)
        }
        else {
            _db.Characters.delete(creature.id!!)
        }
    }

    fun getCharacters(callback: (List<Creature>) -> Unit) {
        _db.Characters.getAll(callback)
    }

    fun getCharacter(id: Long, callback: (Creature) -> Unit) {
        _db.Characters.get(id, callback)
    }

    fun getMonsters(callback: (List<Creature>) -> Unit) {
        _db.Monsters.getAll(callback)
    }

    fun update(creature: Creature) {
        if(creature.isMonster) {
            _db.Monsters.update(creature)
        }
        else {
            _db.Characters.update(creature)
        }
    }
}