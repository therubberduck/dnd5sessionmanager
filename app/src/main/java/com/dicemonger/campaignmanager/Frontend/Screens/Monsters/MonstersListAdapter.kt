package com.dicemonger.campaignmanager.Frontend.Screens.Monsters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Monster
import com.dicemonger.campaignmanager.R

class MonstersListAdapter(items: List<Monster>, private val listener: ObjectListAdapterListener<Monster>, listitem: RecyclerView) :
        ObjectListAdapter<Monster, MonstersListAdapter.ViewHolder>(items, listener.getContext(), listitem) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_character, null)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.rltCell.setOnClickListener { listener.itemClicked(item, it) }
        holder.rltCell.setOnLongClickListener { listener.itemLongClicked(item, it);  true }

        val text = item.name + " (" + item.prefixInitBonus + ")"
        holder.txtName.setText(text)
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val rltCell = cell.findViewById<RelativeLayout>(R.id.rltCell)
        val txtName = cell.findViewById<TextView>(R.id.txtName)
    }
}