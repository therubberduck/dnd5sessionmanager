package com.dicemonger.campaignmanager.Model

import java.util.*

data class Creature(val name: String, val initBonus: Int, var currentInit: Int) {
    val prefixInitBonus: String
        get() {
            if(initBonus < 0) {
                return initBonus.toString()
            }
            return "+" + initBonus
        }

    fun rollInitiative() {
        currentInit = Random().nextInt(20) + 1 + initBonus
    }
}