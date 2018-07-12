package com.dicemonger.campaignmanager.Frontend.Screens.Credits

import android.content.Context
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import com.dicemonger.campaignmanager.R
import com.wealthfront.magellan.BaseScreenView

class CreditsView (context: Context?) : BaseScreenView<CreditsScreen>(context) {
    init {
        View.inflate(context, R.layout.vw_credits, this)

        val txtCredits = findViewById<TextView>(R.id.txtCredits)
        txtCredits.movementMethod = LinkMovementMethod.getInstance()
    }


}