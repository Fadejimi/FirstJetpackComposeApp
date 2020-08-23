package com.fadejimi.callrepresentatives

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fadejimi.callrepresentatives.model.Representative
import com.google.android.material.button.MaterialButton

class RepresentativesAdapter(val context: Context) : RecyclerView.Adapter<MyViewHolder>(), Filterable {

    var items: List<Representative> = ArrayList()
    set(value) {
        field = value
        this.notifyDataSetChanged()
    }

    var filteredItems: List<Representative> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val rep = items.get(position)
        holder.nameTV.text = rep.name
        if (rep.email != "") holder.emailTV.text = rep.email
        else {
            holder.emailTV.visibility = View.GONE
            holder.emailBtn.isEnabled = false
        }
        if (rep.number != "") holder.numberTV.text = rep.number
        else {
            holder.numberTV.visibility = View.GONE
            holder.callBtn.isEnabled = false
            holder.messageBtn.isEnabled = false
        }
        holder.stateTV.text = rep.stateValue

        holder.emailBtn.setOnClickListener {
            Toast.makeText(context, "Email btn clicked", Toast.LENGTH_SHORT).show()
        }
        holder.callBtn.setOnClickListener {
            Toast.makeText(context, "Call btn clicked", Toast.LENGTH_SHORT).show()
        }
        holder.messageBtn.setOnClickListener {
            Toast.makeText(context, "Message btn clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val st = charSequence.toString()
                filteredItems = if (st.isEmpty()) {
                    items
                } else {
                    items.filter { it.state.contains(st) }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredItems
                return filterResults
            }

            override fun publishResults(filter: CharSequence?, filterResults: FilterResults?) {
                filteredItems = filterResults?.values as? ArrayList<Representative> ?: ArrayList()
                notifyDataSetChanged()
            }

        }

    }

}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTV = view.findViewById<TextView>(R.id.nameTextView)
    val stateTV = view.findViewById<TextView>(R.id.stateTextView)
    val emailTV = view.findViewById<TextView>(R.id.emailTextView)
    val numberTV = view.findViewById<TextView>(R.id.numberTextView)

    val emailBtn = view.findViewById<MaterialButton>(R.id.emailBtn)
    val callBtn = view.findViewById<MaterialButton>(R.id.callBtn)
    val messageBtn = view.findViewById<MaterialButton>(R.id.messageBtn)
}

