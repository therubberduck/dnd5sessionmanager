package com.dicemonger.campaignmanager.Frontend.Screens.Initiative.Views

import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import com.dicemonger.campaignmanager.Frontend.Dialogs.TextDialog
import com.dicemonger.campaignmanager.Frontend.Screens.Initiative.ViewModel.CombatantDbo
import com.dicemonger.campaignmanager.Model.Condition
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R
import com.dicemonger.campaignmanager.Utility.dp
import org.apmem.tools.layouts.FlowLayout

class ConditionButton(context: Context, parentView: ViewGroup, condition: Condition, parentItem: CombatantDbo) : Button(context) {
    init {
        setText(condition.name)
        setBackgroundResource(R.drawable.bg_accent_border_rounded)
        val params = FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, 36.dp())
        params.setMargins(4.dp(),4.dp(),4.dp(),4.dp())
        layoutParams = params

        setPadding(8.dp(),8.dp(),8.dp(),8.dp())

        setOnClickListener { TextDialog.show(condition.name, condition.description, context){} }
        setOnLongClickListener {
            parentView.removeView(this);
            parentItem.conditions.remove(condition)
            true }
    }
}