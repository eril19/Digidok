package com.example.digidok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PengajuanKerjasamaDetailActivity : AppCompatActivity() {

    var data : PengajuanKerjasamaModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail)


        data = intent.getParcelableExtra("PengajuanKerjasama")
//        samain sama i.putExtra!

    }
}