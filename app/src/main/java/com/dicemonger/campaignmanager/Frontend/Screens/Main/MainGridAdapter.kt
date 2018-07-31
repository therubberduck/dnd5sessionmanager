package com.dicemonger.campaignmanager.Frontend.Screens.Main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.ScreenItem
import com.dicemonger.campaignmanager.R

class MainGridAdapter(items: List<ScreenItem>, private val listener: ObjectListAdapterListener<ScreenItem>, rcView: RecyclerView) :
        ObjectListAdapter<ScreenItem, MainGridAdapter.ViewHolder>(items, listener.getContext(), rcView) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val cell = _inflater.inflate(R.layout.cell_mainmenu, null)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.imgIcon.setImageResource(item.imageId)
        holder.txtScreenName.setText(item.screenNameId)

        holder.lnrCell.setOnClickListener { listener.itemClicked(item, holder.lnrCell) }
    }

    class ViewHolder(val cell: View) : RecyclerView.ViewHolder(cell) {
        val lnrCell = cell.findViewById<LinearLayout>(R.id.lnrCell)
        val imgIcon = cell.findViewById<ImageView>(R.id.imgIcon)
        val txtScreenName = cell.findViewById<TextView>(R.id.txtScreenName)
    }
}