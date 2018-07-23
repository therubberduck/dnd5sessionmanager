package com.dicemonger.campaignmanager.Database.Modules

import com.dicemonger.campaignmanager.Database.AppDatabase
import com.dicemonger.campaignmanager.Database.Schemas.CharacterSchema
import com.dicemonger.campaignmanager.Database.Schemas.MonsterSchema
import com.dicemonger.campaignmanager.Model.Creature
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import java.util.concurrent.CountDownLatch

class CreatureModule(db: AppDatabase) : DbModule(db) {

    fun getAll(callback: (List<Creature>) -> Unit){
        _db.use {
            val c = CountDownLatch(2)

            var characters : List<Creature>? = null
            select(CharacterSchema.tableName).orderBy(CharacterSchema.name).exec {
                characters = parseList(CharacterSchema.rowParser)
                c.countDown()
            }

            var monsters : List<Creature>? = null
            select(MonsterSchema.tableName).orderBy(MonsterSchema.name).exec {
                monsters = parseList(MonsterSchema.rowParser)
                c.countDown()
            }

            c.await()
            val creatures = characters!!.toMutableList()
            creatures.addAll(monsters!!)

            callback(creatures)
        }
    }

}