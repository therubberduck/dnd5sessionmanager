package com.dicemonger.campaignmanager.Frontend.Screens.Characters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.dicemonger.campaignmanager.Frontend.Screens.Initiative.InitiativeListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Character
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

class CharactersListAdapter(items: List<Character>, private val listener: ObjectListAdapterListener<Character>, listitem: RecyclerView) :
        ObjectListAdapter<Character, CharactersListAdapter.ViewHolder>(items, listener.getContext(), listitem) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_character, null)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.rltCell.setOnClickListener { listener.itemClicked(item, holder.rltCell) }
        holder.rltCell.setOnLongClickListener { listener.itemLongClicked(item, holder.rltCell);  true }

        val text = item.name + " (" + item.prefixInitBonus + ")"
        holder.txtName.setText(text)
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val rltCell = cell.findViewById<RelativeLayout>(R.id.rltCell)
        val txtName = cell.findViewById<TextView>(R.id.txtName)
    }
}