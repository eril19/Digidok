package com.example.digidok.DaftarKJPP

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R

class KjppDetailActivity : AppCompatActivity() {

    var data: DaftarKjppModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kjpp_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("daftarKJPP")

        val tutup = findViewById<Button>(R.id.close_detail_btn)
        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        val nomer = findViewById<TextView>(R.id.no_indukdetail)
        val nama = findViewById<TextView>(R.id.namadetailkjpp)
        val noperizinan = findViewById<TextView>(R.id.no_perizinandetail)
        val tglperizinan = findViewById<TextView>(R.id.tgl_perizinandetail)
        val klasifikasi = findViewById<TextView>(R.id.klasifikasi_perizinandetail)
        val telp = findViewById<TextView>(R.id.telpdetail)
        val alamat = findViewById<TextView>(R.id.alamatdetail)


        tutup.setOnClickListener {
            onBackPressed()
        }

        backArrow.setOnClickListener {
            onBackPressed()
        }

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Detail KJPP")

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@KjppDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@KjppDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@KjppDetailActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@KjppDetailActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@KjppDetailActivity, NotificationActivity::class.java))
        }

        nomer.text = data?.no_kjpp
        nama.text = data?.nama_kjpp
        noperizinan.text = data?.no_perizinan
        tglperizinan.text =  data?.tgl_perizinan
        klasifikasi.text = data?.klasifikasi_perizinan
        telp.text = data?.telp_kjpp
        alamat.text =  data?.alamat
    }
}