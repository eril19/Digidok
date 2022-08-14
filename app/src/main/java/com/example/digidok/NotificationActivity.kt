package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        supportActionBar?.hide()

        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Notification")
        val back = findViewById<ImageView>(R.id.backbtn)



        back.setOnClickListener {
            val intent = Intent(this@NotificationActivity, MenuActivity::class.java)
            startActivity(intent)
        }
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