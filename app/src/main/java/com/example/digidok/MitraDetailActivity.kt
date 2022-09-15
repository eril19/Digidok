package com.example.digidok

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MitraDetailActivity : AppCompatActivity() {

    var data: DaftarMitraModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail)

        data = intent.getParcelableExtra("daftarMitra")
        val npwp = findViewById<TextView>(R.id.npwp)
        val nama = findViewById<TextView>(R.id.nama)

        npwp.setText(data?.npwp)
        nama.setText(data?.nama_mitra)

    }
}