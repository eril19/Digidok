package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
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
            val intent = Intent(this@NotificationActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, NotificationActivity::class.java))
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