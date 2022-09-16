package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LaporanAsetDetailActivity : AppCompatActivity() {

    var data:LaporanAsetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_aset_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("laporanAset")

        val tutup = findViewById<TextView>(R.id.close_detail_btn)
        val id_pks = findViewById<TextView>(R.id.id_pks)
        val nama_mitra = findViewById<TextView>(R.id.nama_mitra)
        val nilai_pks = findViewById<TextView>(R.id.nilai_pks)
        val jenis_kerjasama = findViewById<TextView>(R.id.jenis_kerjasama)


        tutup.setOnClickListener {
            val i = Intent(this@LaporanAsetDetailActivity, LaporanAsetActivity::class.java)
            startActivity(i)
        }


        id_pks.setText(data?.id_pks)
        nama_mitra.setText(data?.nama_mitra)
        nilai_pks.setText(data?.nilai_pks)
        jenis_kerjasama.setText(data?.jenis_kerjasama)
    }
}