package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PengajuanKerjasamaDetailActivity : AppCompatActivity() {

    var data : PengajuanKerjasamaModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail)

        supportActionBar?.hide()

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity, PengajuanKerjasamaActivity::class.java))
        }

        data = intent.getParcelableExtra("PengajuanKerjasama")
//        samain sama i.putExtra!

    }
}