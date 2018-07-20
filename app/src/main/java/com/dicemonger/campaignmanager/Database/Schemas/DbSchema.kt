package com.dicemonger.campaignmanager.Database.Schemas

import org.jetbrains.anko.db.*

open class DbSchema(val tableName: String) {
    var columns: Array<out Pair<String, SqlType>> = arrayOf()

    protected fun columns(vararg cols: Pair<String, SqlType>) {
        columns = cols
    }
}

