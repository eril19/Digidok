package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class KjppDetailActivity : AppCompatActivity() {

    var data:DaftarKjppModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kjpp_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("daftarKJPP")

        val tutup = findViewById<Button>(R.id.close_detail_btn)


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

        nomer.text = data?.no_kjpp
        nama.text = data?.nama_kjpp
        noperizinan.text = data?.no_perizinan
        tglperizinan.text =  data?.tgl_perizinan
        klasifikasi.text = data?.klasifikasi_perizinan
        telp.text = data?.telp_kjpp
        alamat.text =  data?.alamat
    }
}