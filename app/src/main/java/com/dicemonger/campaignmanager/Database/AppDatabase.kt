package com.dicemonger.campaignmanager.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicemonger.campaignmanager.Database.Modules.CharacterModule
import com.dicemonger.campaignmanager.Database.Modules.MonsterModule
import com.dicemonger.campaignmanager.Database.Schemas.CharacterSchema
import com.dicemonger.campaignmanager.Database.Schemas.DbSchema
import com.dicemonger.campaignmanager.Database.Schemas.MonsterSchema
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.createTable

class AppDatabase(val context: Context) : ManagedSQLiteOpenHelper(context, dbName, null, dbVersion) {

    companion object {
        private val dbName = "craftdb"
        private val dbVersion = 1
    }

    private val schemas: List<DbSchema> =  listOf(CharacterSchema(), MonsterSchema())
    val Characters = CharacterModule(this)
    val Monsters = MonsterModule(this)

    override fun onCreate(db: SQLiteDatabase) {
        for(schema in schemas) {
            db.createTable(schema.tableName, true, *schema.columns)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}