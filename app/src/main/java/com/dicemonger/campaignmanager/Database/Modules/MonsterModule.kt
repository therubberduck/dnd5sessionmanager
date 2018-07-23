package com.dicemonger.campaignmanager.Database.Modules

import com.dicemonger.campaignmanager.Database.AppDatabase
import com.dicemonger.campaignmanager.Database.Schemas.MonsterSchema
import com.dicemonger.campaignmanager.Model.Monster
import org.jetbrains.anko.db.*

class MonsterModule(db: AppDatabase) : DbModule(db) {

    fun add(monster: Monster, callback: ((Monster) -> Unit)?) {
        _db.use {
            val name = monster.name
            val init = monster.initBonus

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

    fun get(id: Long, callback: (Monster) -> Unit) {
        _db.use {
            val whereString = MonsterSchema.id + " = " + id
            select(MonsterSchema.tableName).whereArgs(whereString).exec {
                val item = parseSingle(MonsterSchema.rowParser)
                callback(item)
            }
        }
    }

    fun getAll(callback: (List<Monster>) -> Unit){
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

    fun update(monster: Monster) {
        _db.use {
            val name = monster.name
            val init = monster.initBonus

            val whereString = MonsterSchema.id + " = " + monster.id

            update(MonsterSchema.tableName,
                    MonsterSchema.name to name, MonsterSchema.init to init)
                    .whereArgs(whereString)
                    .exec()
        }
    }
}