package com.dicemonger.campaignmanager.Model

import com.dicemonger.campaignmanager.Frontend.Screens.Characters.CharactersScreen
import com.dicemonger.campaignmanager.Frontend.Screens.Credits.CreditsScreen
import com.dicemonger.campaignmanager.Frontend.Screens.Initiative.InitiativeScreen
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.Screen

data class ScreenItem(val index: Int, val imageId: Int, val screenId: Int, val screenNameId: Int) {
    companion object {
        fun getAll() : List<ScreenItem> {
            return listOf(
                    ScreenItem(0, R.drawable.ic_initiative,0, R.string.initiative),
                    ScreenItem(100, R.drawable.ic_character,1, R.string.characters),
                    ScreenItem(500, R.drawable.ic_credits, 2, R.string.credits)
            )
        }
    }

    fun getScreenObject() : Screen<*> {
        when(screenId) {
            0 -> return InitiativeScreen()
            1 -> return CharactersScreen()
            2 -> return CreditsScreen()
            else -> return CreditsScreen()
        }
    }
}