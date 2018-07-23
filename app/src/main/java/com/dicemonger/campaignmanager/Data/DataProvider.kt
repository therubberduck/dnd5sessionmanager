package com.dicemonger.campaignmanager.Data

import android.content.Context
import com.dicemonger.campaignmanager.Database.AppDatabase
import com.dicemonger.campaignmanager.Model.Character
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.Model.Monster

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

    fun add(character: Character, callback: ((Character) -> Unit)?) {
        _db.Characters.add(character, callback)
    }

    fun add(monster: Monster, callback: ((Monster) -> Unit)?) {
        _db.Monsters.add(monster, callback)
    }

    fun delete(character: Character) {
        _db.Characters.delete(character.id)
    }

    fun delete(monster: Monster) {
        _db.Monsters.delete(monster.id)
    }

    fun getCharacters(callback: (List<Character>) -> Unit) {
        _db.Characters.getAll(callback)
    }

    fun getCharacter(id: Long, callback: (Character) -> Unit) {
        _db.Characters.get(id, callback)
    }

    fun getCreatures(callback: (List<Creature>) -> Unit) {
        _db.Creatures.getAll(callback)
    }

    fun getMonsters(callback: (List<Monster>) -> Unit) {
        _db.Monsters.getAll(callback)
    }

    fun update(character: Character) {
        _db.Characters.update(character)
    }

    fun update(monster: Monster) {
        _db.Monsters.update(monster)
    }
}