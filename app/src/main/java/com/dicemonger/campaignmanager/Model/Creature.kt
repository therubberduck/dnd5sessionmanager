package com.dicemonger.campaignmanager.Model

import java.util.*

open class Creature(val id: Long, val name: String, val initBonus: Int) {

    val isMonster: Boolean
    get() {
        return this is Monster
    }

    val prefixInitBonus: String
        get() {
            if(initBonus < 0) {
                return initBonus.toString()
            }
            return "+" + initBonus
        }
}