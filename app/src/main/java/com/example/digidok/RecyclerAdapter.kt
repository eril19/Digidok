package com.example.digidok

import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewGroupCompat
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    // image list
    private val image = intArrayOf(
        R.drawable.message_icon
    )

    // title list
    private val title = arrayOf(
        "Notification 1",
        "Notification 2",
        "Notification 3"
    )

    // detail list
    private val namaMitra = arrayOf(
        "NAMA MITRA 1",
        "NAMA MITRA 2",
        "NAMA MITRA 3"
    )

    // view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notificationIcon: ImageView
        var notificationTitle: TextView
        var namaMitra: TextView

        init {
            notificationIcon = itemView.findViewById(R.id.notificationIcon)
            notificationTitle = itemView.findViewById(R.id.notificationTitle)
            namaMitra = itemView.findViewById(R.id.namaMitra)

            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()
                Snackbar.make(
                    v, "Click on item ${title[position]}",
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null).show()
            }
        }
    }

    // onCreateViewHolder
    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ViewGroup.context)
            .inflate(R.layout.layout_notification, ViewGroup, false)
        return ViewHolder(v)
    }

    // onBindViewHolder
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.notificationIcon.setImageResource(image[i])
        viewHolder.notificationTitle.text = title[i]
        viewHolder.namaMitra.text = namaMitra[i]
    }

    // getItemCount
    override fun getItemCount(): Int {
        return title.size
    }

}


