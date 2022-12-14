package com.example.digidok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val context: Context, private val notification: List<NotificationModel>, val listener: (NotificationModel) -> Unit)
    : RecyclerView.Adapter<RecyclerAdapter.NotificationViewHolder>(){

    class NotificationViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val notificationTitle = view.findViewById<TextView>(R.id.notificationTitle)
        val notificationDetail = view.findViewById<TextView>(R.id.notificationDetail)
        val NotificationDateTime = view.findViewById<TextView>(R.id.NotificationDateTime)

        fun bindView(NotificationModel: NotificationModel, listener: (NotificationModel) -> Unit){
            notificationTitle.text = NotificationModel.NotificationTitle
            notificationDetail.text = NotificationModel.NotificationDetail
            NotificationDateTime.text = NotificationModel.NotificationDateTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_card_notification, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bindView(notification[position], listener)
    }

    override fun getItemCount(): Int = notification.size

}