package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

class MonstersListAdapter(items: List<Creature>, private val listener: ObjectListAdapterListener<Creature>, listitem: RecyclerView) :
        ObjectListAdapter<Creature, MonstersListAdapter.ViewHolder> (items, listener.getContext(), listitem) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_character, null)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.rltCell.setOnClickListener { listener.itemClicked(item) }
        holder.rltCell.setOnLongClickListener { listener.itemLongClicked(item);  true }

        val text = item.name + " (" + item.prefixInitBonus + ")"
        holder.txtName.setText(text)
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val rltCell = cell.findViewById<RelativeLayout>(R.id.rltCell)
        val txtName = cell.findViewById<TextView>(R.id.txtName)
    }
}