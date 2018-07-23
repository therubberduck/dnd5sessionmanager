package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import com.dicemonger.campaignmanager.Model.Creature
import java.util.*

data class CombatantDbo (val id: Long, val name: String, val initBonus: Int, val isMonster: Boolean, var currentInit: Int) {

    companion object {
        fun create(name: String, initBonus: Int) : CombatantDbo {
            return CombatantDbo(-1, name, initBonus, true, 0)
        }

        fun create(creature: Creature) : CombatantDbo {
            return CombatantDbo(creature.id!!, creature.name, creature.initBonus, creature.isMonster, 0)
        }
    }

    var canAddToList = true
    var isReadied = false

    fun rollInitiative() {
        currentInit = Random().nextInt(20) + 1 + initBonus
    }

    val prefixInitBonus: String
        get() {
            if(initBonus < 0) {
                return initBonus.toString()
            }
            return "+" + initBonus
        }
}