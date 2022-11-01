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
//            val intent = Intent(this@NotificationActivity, DashboardActivity::class.java)
//            startActivity(intent)
            onBackPressed()
        }
        val notificationList = listOf<NotificationModel>(
            NotificationModel(
                NotificationTitle = "PKS-2022-000021 - Tanah",
                NotificationDetail = "BINA NUSANTARA",
                NotificationDateTime = "7 Okt 2022, 11:55"
            ),
            NotificationModel(
                NotificationTitle = "PKS-2022-000022 - Tanah",
                NotificationDetail = "Jakarta Asset Management Center",
                NotificationDateTime = "7 Okt 2022, 11:55"
            ),
            NotificationModel(
                NotificationTitle = "No PKS - Jenis BMD",
                NotificationDetail = "Nama mitra",
                NotificationDateTime = "7 Okt 2022, 11:55"
            ),
            NotificationModel(
                NotificationTitle = "No PKS - Jenis BMD",
                NotificationDetail = "Nama mitra",
                NotificationDateTime = "7 Okt 2022, 11:55"
            ),
            NotificationModel(
                NotificationTitle = "No PKS - Jenis BMD",
                NotificationDetail = "Nama mitra",
                NotificationDateTime = "7 Okt 2022, 11:55"
            ),
            NotificationModel(
                NotificationTitle = "No PKS - Jenis BMD",
                NotificationDetail = "Nama mitra",
                NotificationDateTime = "7 Okt 2022, 11:55"
            ),
            NotificationModel(
                NotificationTitle = "No PKS - Jenis BMD",
                NotificationDetail = "Nama mitra",
                NotificationDateTime = "7 Okt 2022, 11:55"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_notification)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RecyclerAdapter(this, notificationList){

        }
    }
}