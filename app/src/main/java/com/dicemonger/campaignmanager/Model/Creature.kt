package com.dicemonger.campaignmanager.Model

import java.util.*

data class Creature(val id: Long?, val name: String, val initBonus: Int, val isMonster: Boolean, var currentInit: Int) {

    constructor(id: Long?, name: String, initBonus: Int, isMonster: Boolean) : this(id, name, initBonus, isMonster,0)
    constructor(name: String, initBonus: Int, isMonster: Boolean) : this(null, name, initBonus, isMonster,0)

    var isReadied = false

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