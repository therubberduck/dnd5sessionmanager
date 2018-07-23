package com.dicemonger.campaignmanager.Model

import java.util.*

data class Creature(val id: Long?, val name: String, val initBonus: Int, val isMonster: Boolean) {

    constructor(name: String, initBonus: Int, isMonster: Boolean) : this(null, name, initBonus, isMonster)

    val prefixInitBonus: String
        get() {
            if(initBonus < 0) {
                return initBonus.toString()
            }
            return "+" + initBonus
        }
}