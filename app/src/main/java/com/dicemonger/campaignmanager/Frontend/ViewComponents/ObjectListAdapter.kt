package com.dicemonger.campaignmanager.Frontend.ViewComponents

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

/**
 * Created by redwebpraktik on 12/09/2017.
 */

interface ObjectListAdapterListener<T> {
    fun getContext() : Context
    fun itemClicked(item: T, view: View) {}
    fun itemLongClicked(item: T, view: View) {}
}

abstract class ObjectListAdapter<T, U: RecyclerView.ViewHolder>(items: List<T> = ArrayList<T>(), protected val _context: Context, protected val _recyclerView: RecyclerView? = null)
    : RecyclerView.Adapter<U>() {

    protected val _inflater: LayoutInflater
    protected val _items: MutableList<T>

    init {
        _inflater = LayoutInflater.from(_context)
        _items = items.toMutableList()
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

    fun getPosition(item: T) : Int {
        return _items.indexOf(item)
    }

    fun getItems() : List<T> {
        return _items.toList()
    }

    fun removeItem(item: T) {
        _items.remove(item)
        notifyDataSetChanged()
    }

    fun replaceItem(newItem: T, selector: (T) -> Boolean) {
        val oldItem = _items.find(selector)
        _items.remove(oldItem)
        _items.add(newItem)
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