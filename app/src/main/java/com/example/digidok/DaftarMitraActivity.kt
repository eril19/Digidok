package com.example.digidok

import android.widget.ArrayAdapter
import com.example.digidok.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DaftarMitraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listStatus = arrayListOf<String>("[SEMUA]", "AKTIF", "NON AKTIF")
        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listStatus)
        //binding.spinnerStatus.adapter = adapter

        val DaftarMitraList = listOf<DaftarMitraModel>(
            DaftarMitraModel(
                id_mitra = "MT-2000-0001",
                nama_mitra = "PT INDOCATER",
                jenis_mitra = "Perusahaan Swasta",
                status = "Status:",
                status_mitra = "whitelist",
                npwp = "NPWP",
                npwp_mitra = "02.623.519.2-061.000",
                status_aktif = "aktif"
            ),
            DaftarMitraModel(
                id_mitra = "MT-2011-0001",
                nama_mitra = "PT Wahana Nusantara",
                jenis_mitra = "Perusahaan Swasta",
                status = "Status:",
                status_mitra = "whitelist",
                npwp = "NPWP",
                npwp_mitra = "013121280073000",
                status_aktif = "aktif"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_mitra)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = DaftarMitraAdapter(this, DaftarMitraList){

        }

    }

}
