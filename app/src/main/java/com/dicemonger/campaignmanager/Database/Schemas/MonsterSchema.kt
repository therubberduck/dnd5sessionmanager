package com.dicemonger.campaignmanager.Database.Schemas

import com.dicemonger.campaignmanager.Model.Creature
import org.jetbrains.anko.db.*

class MonsterSchema : DbSchema(tableName) {
    companion object {
        val tableName = "Monsters"
        val id = "Id"
        val name = "Name"
        val init = "Init"

        val rowParser = DbMonsterRowParser()
    }

    init {
        columns(
                Pair(id, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
                Pair(name, TEXT),
                Pair(init, INTEGER)
        )
    }
}

class DbMonsterRowParser : RowParser<Creature> {
    override fun parseRow(columns: Array<Any?>): Creature {
        val id = columns[0] as Long
        val name = columns[1] as String
        val init = columns[2] as Long

        val item = Creature(id, name, init.toInt(), true)

        return item
    }

}