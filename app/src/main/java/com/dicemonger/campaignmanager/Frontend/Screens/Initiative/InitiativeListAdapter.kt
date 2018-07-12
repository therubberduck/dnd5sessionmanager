package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

class InitiativeListAdapter(items: List<Creature>, private val listener: ObjectListAdapterListener<Creature>, listview: RecyclerView) :
        ObjectListAdapter<Creature, InitiativeListAdapter.ViewHolder>(items, listener.getContext(), listview) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_initiative, null)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        val text = item.name + "(" + item.currentInit + ")"
        holder.txtScreenName.setText(text)

        holder.rltCell.setOnClickListener { listener.itemClicked(item) }
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val rltCell = cell.findViewById<RelativeLayout>(R.id.rltCell)
        val txtScreenName = cell.findViewById<TextView>(R.id.txtName)
        val btnDelay = cell.findViewById<ImageButton>(R.id.fabDelay)
        val btnRemove = cell.findViewById<ImageButton>(R.id.fabRemove)
    }
}