package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.R

interface InitPickerAdapterListener : ObjectListAdapterListener<CombatantDbo>{
    fun groupAdded(combatant: CombatantDbo)
}

class InitPickerAdapter(items: List<CombatantDbo>, private val listener: InitPickerAdapterListener, listview: RecyclerView) :
        ObjectListAdapter<CombatantDbo, InitPickerAdapter.ViewHolder>(items, listener.getContext(), listview) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): InitPickerAdapter.ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_initpicker, null)
        return InitPickerAdapter.ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if(item.canAddToList) {
            holder.txtScreenName.setText(item.nameWithInitBonus)

            holder.frmCell.setOnClickListener { listener.itemClicked(item) }

            if(item.isMonster) {
                holder.btnGroup.setOnClickListener { listener.groupAdded(item) }
            }
            else {
                holder.btnGroup.visibility = View.INVISIBLE
            }
        }
        else {
            holder.hide()
        }
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val view = cell
        val frmCell = cell.findViewById<ViewGroup>(R.id.frmCell)
        val txtScreenName = cell.findViewById<TextView>(R.id.txtName)
        val btnGroup = cell.findViewById<FloatingActionButton>(R.id.fabQuantity)

        fun hide() {
            view.visibility = View.GONE
            view.layoutParams = RecyclerView.LayoutParams(0,0)
        }
    }
}