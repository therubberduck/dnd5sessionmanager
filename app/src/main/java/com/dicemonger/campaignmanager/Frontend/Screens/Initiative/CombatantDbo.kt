package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import com.dicemonger.campaignmanager.Model.Creature
import java.util.*

data class CombatantDbo (val id: Long, val name: String, val initBonus: Int, val isMonster: Boolean, var currentInit: Int) {

    companion object {
        fun create(name: String, initBonus: Int) : CombatantDbo {
            return CombatantDbo(-1, name, initBonus, true, 0)
        }

        fun create(creature: Creature) : CombatantDbo {
            return CombatantDbo(creature.id, creature.name, creature.initBonus, creature.isMonster, 0)
        }
    }

    var canAddToList = true
    var isReadied = false
    var groupNumber = 0

    fun rollInitiative() {
        currentInit = Random().nextInt(20) + 1 + initBonus
    }

    val nameWithInitBonus : String
        get() {
            return name + " (" + prefixInitBonus + ")"
        }

    val nameWithInit : String
        get() {
            var textString: String

            if(groupNumber > 1) {
                textString = groupNumber.toString() + " " + name + "s"
            }
            else {
                textString = name
            }

            textString += " (" + currentInit + ")"
            return textString
        }

    val prefixInitBonus: String
        get() {
            if(initBonus < 0) {
                return initBonus.toString()
            }
            return "+" + initBonus
        }
}