package com.test.machinetest.ui.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<itemType> : RecyclerView.Adapter<RecyclerView.ViewHolder?>(),
    AdapterUtil<itemType> {

    override fun getItemCount(): Int {
        return itemList.size
    }


    private val itemList = ArrayList<itemType>()

    override fun removeAt(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemAt(position: Int): itemType {
        return itemList[position]
    }

    override fun addAt(position: Int, item: itemType) {
        itemList.add(position, item)
    }

    override fun addItem(item: itemType) {
        val count = itemList.size
        itemList.add(item)
        notifyItemInserted(count)
    }

    override fun setList(items: List<itemType>) {
        val count = itemList.size
        itemList.clear()
        notifyItemRangeRemoved(0, count)
        itemList.addAll(items)
        notifyItemRangeInserted(0, items.size)
    }

    override fun addList(items: List<itemType>) {
        val count = itemList.size
        itemList.addAll(items)
        notifyItemRangeInserted(count, items.size)
    }

}

