package com.dicemonger.campaignmanager.Database.Modules

import com.dicemonger.campaignmanager.Database.AppDatabase
import com.dicemonger.campaignmanager.Database.Schemas.CharacterSchema
import com.dicemonger.campaignmanager.Model.Character
import org.jetbrains.anko.db.*

class CharacterModule(db: AppDatabase) : DbModule(db) {

    fun add(character: Character, callback: ((Character) -> Unit)?) {
        _db.use {
            val name = character.name
            val init = character.initBonus

            val id = insertOrThrow(
                    CharacterSchema.tableName,
                    CharacterSchema.name to name,
                    CharacterSchema.init to init
            )

            if(callback != null) {
                get(id, callback)
            }
        }
    }

    fun get(id: Long, callback: (Character) -> Unit) {
        _db.use {
            val whereString = CharacterSchema.id + " = " + id
            select(CharacterSchema.tableName).whereArgs(whereString).exec {
                val item = parseSingle(CharacterSchema.rowParser)
                callback(item)
            }
        }
    }

    fun getAll(callback: (List<Character>) -> Unit){
        _db.use {
            select(CharacterSchema.tableName).orderBy(CharacterSchema.name).exec {
                val items = parseList(CharacterSchema.rowParser)
                callback(items)
            }
        }
    }

    fun delete(id: Long) {
        val whereString = CharacterSchema.id + " = " + id

        _db.use {
            delete(CharacterSchema.tableName, whereString)
        }
    }

    fun update(character: Character) {
        _db.use {
            val name = character.name
            val init = character.initBonus

            val whereString = CharacterSchema.id + " = " + character.id

            update(CharacterSchema.tableName,
                    CharacterSchema.name to name, CharacterSchema.init to init)
                    .whereArgs(whereString)
                    .exec()
        }
    }
}