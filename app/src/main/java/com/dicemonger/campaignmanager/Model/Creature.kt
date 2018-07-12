package com.dicemonger.campaignmanager.Model

data class Creature(val name: String, val initBonus: Int, val currentInit: Int) {
    val prefixInitBonus: String
        get() {
            if(initBonus < 0) {
                return initBonus.toString()
            }
            return "+" + initBonus
        }
}