package com.tangentem.triplebyteapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tangentem.triplebyteapp.model.Item
import com.tangentem.triplebyteapp.model.SubItem

class ItemAdapter(val items: List<Item>, val context: Context) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, p1: Int): ItemViewHolder {
        if (getItemType(p1)) return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.header_item_view, view, false))
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, view, false))
    }

    override fun getItemCount(): Int {
        var result = 0
        for (item in items) {
            result += item.sub_items.size + 1
        }
        return result
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (items.)
        val item = items.get(position)
        holder.nameView.text = item.name
        holder.dateView.text = item.date
        holder.priceView.text = item.price
    }

    fun getItemType(position: Int) : Boolean {
        var result = 0
        var max_items = 0
        for (item in items) {
            max_items += item.sub_items.size + 1
            if (position < max_items) return false
            else if (position == max_items) return true
        }
        return false
    }

}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameView: TextView = itemView.findViewById(R.id.name_view)
    val dateView: TextView = itemView.findViewById(R.id.date_view)
    val priceView: TextView = itemView.findViewById(R.id.price_view)
}

class HeaderViewHolder(headerView: View): RecyclerView.ViewHolder(headerView) {
    val nameView: TextView = itemView.findViewById(R.id.name_view)
}