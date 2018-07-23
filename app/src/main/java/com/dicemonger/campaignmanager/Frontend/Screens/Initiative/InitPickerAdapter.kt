package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.Creature
import com.dicemonger.campaignmanager.R

class InitPickerAdapter(items: List<CombatantDbo>, private val listener: ObjectListAdapterListener<CombatantDbo>, listview: RecyclerView) :
        ObjectListAdapter<CombatantDbo, InitPickerAdapter.ViewHolder>(items, listener.getContext(), listview) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): InitPickerAdapter.ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_initpicker, null)
        return InitPickerAdapter.ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if(item.canAddToList) {
            val text = item.name + " (" + item.prefixInitBonus + ")"
            holder.txtScreenName.setText(text)

            holder.frmCell.setOnClickListener { listener.itemClicked(item) }
        }
        else {
            holder.frmCell.visibility = View.GONE
        }
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val frmCell = cell.findViewById<ViewGroup>(R.id.frmCell)
        val txtScreenName = cell.findViewById<TextView>(R.id.txtName)
    }
}