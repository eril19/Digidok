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
                NotificationTitle = "PT. Rifdah SegarBugar",
                NotificationDetail = "Banyakin gerak plis"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "PT. Kaepee",
                NotificationDetail = "Semangat bikin cv"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "CV. Cupapi Munyenyo",
                NotificationDetail = "awokwokwowko"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "PT. Preefort",
                NotificationDetail = "Minyak Bumi mantab"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Universitas Binus",
                NotificationDetail = "Bayaran jangan lupa"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "BAPD",
                NotificationDetail = "Lahan kosong deket cipayung"
            ),
            NotificationModel(
                R.drawable.ic_baseline_mail_outline_24,
                NotificationTitle = "Kanye West",
                NotificationDetail = "Bayar utang jgn lupa"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_notification)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RecyclerAdapter(this, notificationList){

        }
    }
}