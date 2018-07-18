package com.dicemonger.campaignmanager.Frontend.Screens

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.AbsListView
import com.dicemonger.campaignmanager.Model.Creature

/**
 * Created by redwebpraktik on 12/09/2017.
 */

interface ObjectListAdapterListener<T> {
    fun getContext() : Context
    fun itemClicked(item: T)
}

abstract class ObjectListAdapter<T, U: RecyclerView.ViewHolder> : RecyclerView.Adapter<U> {

    protected val _context: Context
    protected val _inflater: LayoutInflater
    protected val _listener: ObjectListAdapterListener<T>?
    protected val _items: MutableList<T>
    protected val _recyclerview: RecyclerView?

    constructor(items: List<T> = ArrayList<T>(), context: Context, recyclerView: RecyclerView? = null){
        _context = context
        _inflater = LayoutInflater.from(_context)
        _listener = null
        _items = items.toMutableList()
        _recyclerview = recyclerView
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

    fun sortByDouble(selector: (T) -> Double) {
        _items.sortBy({selector(it)})
        notifyDataSetChanged()
    }

    fun sortByInt(selector: (T) -> Int) {
        _items.sortBy({selector(it)})
        notifyDataSetChanged()
    }

    fun sortByString(selector: (T) -> String) {
        _items.sortBy({selector(it)})
        notifyDataSetChanged()
    }

    //Override Methods

    override fun getItemCount(): Int {
        return _items.size
    }

    fun getItem(i: Int): T {
        return _items.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }
}