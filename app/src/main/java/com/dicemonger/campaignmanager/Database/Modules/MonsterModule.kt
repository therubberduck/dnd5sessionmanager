package com.dicemonger.campaignmanager.Database.Modules

import com.dicemonger.campaignmanager.Database.AppDatabase
import com.dicemonger.campaignmanager.Database.Schemas.CharacterSchema
import com.dicemonger.campaignmanager.Database.Schemas.MonsterSchema
import com.dicemonger.campaignmanager.Model.Creature
import org.jetbrains.anko.db.*

class MonsterModule(db: AppDatabase) : DbModule(db) {

    fun add(creature: Creature, callback: ((Creature) -> Unit)?) {
        _db.use {
            val name = creature.name
            val init = creature.initBonus

            val id = insertOrThrow(
                    MonsterSchema.tableName,
                    MonsterSchema.name to name,
                    MonsterSchema.init to init
            )

            if(callback != null) {
                get(id, callback)
            }
        }
    }

    fun get(id: Long, callback: (Creature) -> Unit) {
        _db.use {
            val whereString = MonsterSchema.id + " = " + id
            select(MonsterSchema.tableName).whereArgs(whereString).exec {
                val item = parseSingle(MonsterSchema.rowParser)
                callback(item)
            }
        }
    }

    fun getAll(callback: (List<Creature>) -> Unit){
        _db.use {
            select(MonsterSchema.tableName).orderBy(MonsterSchema.name).exec {
                val items = parseList(MonsterSchema.rowParser)
                callback(items)
            }
        }
    }

    fun delete(id: Long) {
        val whereString = MonsterSchema.id + " = " + id

        _db.use {
            delete(MonsterSchema.tableName, whereString)
        }
    }

    fun update(creature: Creature) {
        _db.use {
            val name = creature.name
            val init = creature.initBonus

            val whereString = MonsterSchema.id + " = " + creature.id

            update(MonsterSchema.tableName,
                    MonsterSchema.name to name, MonsterSchema.init to init)
                    .whereArgs(whereString)
                    .exec()
        }
    }
}