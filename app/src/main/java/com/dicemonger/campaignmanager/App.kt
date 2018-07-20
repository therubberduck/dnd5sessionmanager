package com.dicemonger.campaignmanager

import android.app.Application
import com.dicemonger.campaignmanager.Data.DataProvider
import com.dicemonger.campaignmanager.Database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DataProvider.initialize(this)
    }
}