package com.dicemonger.campaignmanager.Frontend.Screens.Initiative

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.ViewComponents.ObjectListAdapterListener
import com.dicemonger.campaignmanager.R
import com.dicemonger.campaignmanager.Utility.dp
import com.dicemonger.campaignmanager.Utility.moveWithInBounds
import com.dicemonger.campaignmanager.ViewModel.CombatantDbo
import org.apmem.tools.layouts.FlowLayout

interface InitiativeListListener : ObjectListAdapterListener<CombatantDbo> {
    fun initReady(combatant: CombatantDbo)
    fun initRemove(combatant: CombatantDbo)
    fun initRemoveAll(combatant: CombatantDbo)
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

        if(item.conditions.isEmpty()) {
            holder.flwConditions.visibility = View.GONE
        }
        else {
            holder.flwConditions.visibility = View.VISIBLE
            holder.flwConditions.removeAllViews()
            item.conditions.forEach {
                val btnCondition = Button(_context)
                btnCondition.setText(it)
                btnCondition.setBackgroundResource(R.drawable.bg_accent_border_rounded)
                val params = FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, 36.dp())
                params.setMargins(4.dp(),4.dp(),4.dp(),4.dp())
                btnCondition.layoutParams = params

                btnCondition.setPadding(8.dp(),8.dp(),8.dp(),8.dp())

                holder.flwConditions.addView(btnCondition)

                btnCondition.setOnClickListener { holder.flwConditions.removeView(btnCondition) }
            }
        }

        holder.rltCell.setOnClickListener { listener.itemClicked(item, it) }

        holder.btnRemove.setOnClickListener { listener.initRemove(item) }
        holder.btnRemove.setOnLongClickListener { listener.initRemoveAll(item); true }
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

        //Avoid crash in case of error
        listPosition = listPosition.moveWithInBounds(0, itemCount)

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
        val btnRemove = cell.findViewById<ImageButton>(R.id.fabRemove)
        val flwConditions = cell.findViewById<FlowLayout>(R.id.flwConditions)
    }
}