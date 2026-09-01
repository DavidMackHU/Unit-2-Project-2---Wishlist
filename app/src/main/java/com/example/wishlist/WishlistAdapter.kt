package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val items: MutableList<WishlistItem>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTv: TextView = itemView.findViewById(R.id.itemNameTv)
        val itemPriceTv: TextView = itemView.findViewById(R.id.itemPriceTv)
        val itemUrlTv: TextView = itemView.findViewById(R.id.itemUrlTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemNameTv.text = item.itemName
        holder.itemPriceTv.text = item.itemPrice
        holder.itemUrlTv.text = item.itemUrl

        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.itemUrl))
                it.context.startActivity(browserIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL or no browser found", Toast.LENGTH_SHORT).show()
            }
        }

        holder.itemView.setOnLongClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
