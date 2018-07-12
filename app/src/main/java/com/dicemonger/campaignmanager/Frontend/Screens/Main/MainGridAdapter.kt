package com.dicemonger.campaignmanager.Frontend.Screens.Main

import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapter
import com.dicemonger.campaignmanager.Frontend.Screens.ObjectListAdapterListener
import com.dicemonger.campaignmanager.Model.ScreenItem
import com.dicemonger.campaignmanager.R

class MainGridAdapter(items: List<ScreenItem>, listener: ObjectListAdapterListener<ScreenItem>, gridview: GridView) : ObjectListAdapter<ScreenItem>(items, listener, gridview) {
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        var cell: View
        var viewHolder: ViewHolder
        if (convertView != null) {
            cell = convertView
            viewHolder = cell.tag as ViewHolder
        } else {
            cell = _inflater.inflate(R.layout.cell_mainmenu, null)

            val lnrCell = cell.findViewById<LinearLayout>(R.id.lnrCell)
            val imgIcon = cell.findViewById<ImageView>(R.id.imgIcon)
            val txtScreenName = cell.findViewById<TextView>(R.id.txtScreenName)

            viewHolder = ViewHolder(lnrCell, imgIcon, txtScreenName)
            cell.tag = viewHolder
        }

        val item = getItem(position)

        viewHolder.imgIcon.setImageResource(item.imageId)
        viewHolder.txtScreenName.setText(item.screenNameId)

        return cell
    }

    data class ViewHolder(val lnrCell: LinearLayout, val imgIcon: ImageView, val txtScreenName: TextView)
}