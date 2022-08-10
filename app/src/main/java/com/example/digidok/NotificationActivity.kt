package com.example.digidok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val notificationList = listOf<NotificationModel>(
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Notification 1",
                NotificationDetail = "Notification Detail"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Notification 2",
                NotificationDetail = "Notification Detail"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Notification 3",
                NotificationDetail = "Notification Detail"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Notification 4",
                NotificationDetail = "Notification Detail"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Notification 5",
                NotificationDetail = "Notification Detail"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Notification 6",
                NotificationDetail = "Notification Detail"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Notification 7",
                NotificationDetail = "Notification Detail"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_notification)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RecyclerAdapter(this, notificationList){

        }
    }
}