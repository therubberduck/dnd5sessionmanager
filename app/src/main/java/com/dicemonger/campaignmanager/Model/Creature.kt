package com.dicemonger.campaignmanager.Model

import java.util.*

data class Creature(val id: Long?, val name: String, val initBonus: Int, var currentInit: Int) {

    constructor(id: Long?, name: String, initBonus: Int) : this(id, name, initBonus, 0)
    constructor(name: String, initBonus: Int) : this(null, name, initBonus, 0)

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