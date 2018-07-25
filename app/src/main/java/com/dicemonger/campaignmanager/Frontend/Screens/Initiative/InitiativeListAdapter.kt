package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.R

interface InitiativeListListener : ObjectListAdapterListener<CombatantDbo> {
    fun initReady(combatant: CombatantDbo)
    fun initRemove(combatant: CombatantDbo)
}

class InitiativeListAdapter(items: List<CombatantDbo>, private val listener: InitiativeListListener, listview: RecyclerView) :
        ObjectListAdapter<CombatantDbo, InitiativeListAdapter.ViewHolder>(items, listener.getContext(), listview) {

    var currentSelected: Int = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_initiative, null)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(adjustPosition(position))

        val isActive = position == 0
        holder.vwIsActive.isActive = isActive

        holder.txtScreenName.setText(item.nameWithInit)

        if(item.isReadied) {
            holder.vwIsActive.isReady = true
        }
        else {
            holder.vwIsActive.isReady = false
        }

        holder.btnReady.setOnClickListener { listener.initReady(item) }
        holder.btnRemove.setOnClickListener { listener.initRemove(item) }
    }

    fun notifyItemChanged(item: CombatantDbo) {
        val index = getPosition(item)
        val adjustedIndex = oppositeAdjustPosition(index)
        notifyItemChanged(adjustedIndex)
    }

    fun adjustPosition(arrayPosition: Int) : Int {
        var listPosition = arrayPosition + currentSelected
        if(listPosition >= itemCount) {
            listPosition = listPosition - itemCount
        }
        return listPosition
    }

    fun oppositeAdjustPosition(listPosition: Int) : Int {
        var arrayPosition = listPosition - currentSelected
        if(arrayPosition < 0) {
            arrayPosition = arrayPosition + itemCount
        }
        return arrayPosition
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val rltCell = cell.findViewById<RelativeLayout>(R.id.rltCell)
        val txtScreenName = cell.findViewById<TextView>(R.id.txtName)
        val vwIsActive = cell.findViewById<InitStateLinearLayout>(R.id.lnrActive)
        val btnReady = cell.findViewById<ImageButton>(R.id.fabReady)
        val btnRemove = cell.findViewById<ImageButton>(R.id.fabRemove)
    }
}