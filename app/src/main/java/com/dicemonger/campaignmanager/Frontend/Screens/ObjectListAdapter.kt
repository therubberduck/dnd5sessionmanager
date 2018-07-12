package com.dicemonger.campaignmanager.Frontend.Screens

import android.content.Context
import android.view.LayoutInflater
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView

/**
 * Created by redwebpraktik on 12/09/2017.
 */

interface ObjectListAdapterListener<T> {
    fun getContext() : Context
    fun itemClicked(item: T)
}

abstract class ObjectListAdapter<T> : BaseAdapter {

    protected val _context: Context
    protected val _inflater: LayoutInflater
    protected val _listener: ObjectListAdapterListener<T>?
    protected val _items: MutableList<T>
    protected val _listview: AbsListView?

    constructor(items: List<T> = ArrayList<T>(), listener: ObjectListAdapterListener<T>, listview: AbsListView? = null) {
        _context = listener.getContext()
        _inflater = LayoutInflater.from(_context)
        _listener = listener
        _items = items.toMutableList()
        _listview = listview

        if(listview != null) {
            listview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                listener.itemClicked(getItem(position))
            }
        }
    }

    constructor(items: List<T> = ArrayList<T>(), context: Context, listview: AbsListView? = null){
        _context = context
        _inflater = LayoutInflater.from(_context)
        _listener = null
        _items = items.toMutableList()
        _listview = listview
    }

    // List Manipulation

    fun addItem(item: T) {
        _items.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        _items.clear();
        notifyDataSetChanged()
    }

    fun getItems() : List<T> {
        return _items.toList()
    }

    fun removeItem(item: T) {
        _items.remove(item)
        notifyDataSetChanged()
    }

    fun setItems(items: List<T>) {
        _items.clear()
        _items.addAll(items)
        notifyDataSetChanged()
    }

    //Listener Handling

    protected fun getParentContext() : Context? {
        return _listener?.getContext()
    }

    protected fun itemClicked(item: T) {
        _listener?.itemClicked(item)
    }

    //Override Methods

    override fun getCount(): Int {
        return _items.size;
    }

    override fun getItem(i: Int): T {
        return _items.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
}