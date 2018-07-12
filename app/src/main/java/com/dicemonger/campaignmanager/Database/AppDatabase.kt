package com.dicemonger.campaignmanager.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class AppDatabase(val context: Context) : ManagedSQLiteOpenHelper(context, "craftdb", null, dbVersion) {

    companion object {
        private val dbVersion = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}