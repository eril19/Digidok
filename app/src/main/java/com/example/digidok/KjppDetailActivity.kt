package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class KjppDetailActivity : AppCompatActivity() {

    var data:DaftarKjppModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kjpp_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("daftarKJPP")

        val tutup = findViewById<TextView>(R.id.close_detail_btn)
        val nama = findViewById<TextView>(R.id.nama)
        val noperizinan = findViewById<TextView>(R.id.no_perizinan)
        val tglperizinan = findViewById<TextView>(R.id.tgl_perizinan)
        val klasifikasi = findViewById<TextView>(R.id.klasifikasi_perizinan)
        val telp = findViewById<TextView>(R.id.telp)
        val alamat = findViewById<TextView>(R.id.alamat)


        tutup.setOnClickListener {
            val i = Intent(this@KjppDetailActivity, DaftarKjppActivity::class.java)
            startActivity(i)
        }


        nama.setText(data?.nama_kjpp)
        noperizinan.setText(data?.no_perizinan)
        tglperizinan.setText(data?.tgl_perizinan)
        klasifikasi.setText(data?.klasifikasi_perizinan)
        telp.setText(data?.telp_kjpp)
        alamat.setText(data?.alamat)
    }
}