package com.dicemonger.campaignmanager

import android.app.Application
import com.dicemonger.campaignmanager.Database.AppDatabase

class App : Application() {
    private lateinit var _db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        _db = AppDatabase(this)
    }

    fun getDatabase(): AppDatabase {
        return _db
    }
}