package com.dicemonger.campaignmanager.Data

import com.dicemonger.campaignmanager.Model.Creature

class DataProvider {

    companion object {
        private val _instance = DataProvider()

        fun get() : DataProvider {
            return _instance
        }
    }

    fun getCharacters() : List<Creature> {
        return listOf(
                Creature("Samuel", 2, 0),
                Creature("Heinrich", -1, 0),
                Creature("Big Boy", 4, 0),
                Creature("Master", 2, 0)
        )
    }
}