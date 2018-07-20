package com.dicemonger.campaignmanager.Database.Schemas

import com.dicemonger.campaignmanager.Model.Creature
import org.jetbrains.anko.db.*

class CharacterSchema : DbSchema(tableName) {
    companion object {
        val tableName = "Characters"
        val id = "Id"
        val name = "Name"
        val init = "Init"

        val rowParser = DbCharacterRowParser()
    }

    init {
        columns(
                Pair(id, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
                Pair(name, TEXT),
                Pair(init, INTEGER)
        )
    }
}

class DbCharacterRowParser : RowParser<Creature> {
    override fun parseRow(columns: Array<Any?>): Creature {
        val id = columns[0] as Long
        val name = columns[1] as String
        val init = columns[2] as Long

        val item = Creature(id, name, init.toInt())

        return item
    }

}