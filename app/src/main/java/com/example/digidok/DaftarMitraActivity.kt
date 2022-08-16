package com.example.digidok

import android.content.Intent
import android.graphics.Color
import android.widget.ArrayAdapter
import com.example.digidok.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DaftarMitraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_daftar_mitra)

        val listStatus = arrayListOf<String>("[SEMUA]", "AKTIF", "NON AKTIF")
        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listStatus)
        //binding.spinnerStatus.adapter = adapter

        supportActionBar?.hide()

        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Daftar Mitra")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@DaftarMitraActivity, MenuActivity::class.java)
            startActivity(intent)
        }

        //val bg:TextView = findViewById(R.id.header_color)



        val DaftarMitraList = listOf<DaftarMitraModel>(
            DaftarMitraModel(
                header_color = "Aktif",
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
                header_color = "Tidak Aktif",
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

//        if (bg.text.equals("Tidak Aktif") ){
//            bg.setBackgroundColor(Color.RED)
//
//        }
//        else if (bg.text.equals("Aktif") ){
//            bg.setBackgroundColor(Color.GREEN)
//        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_mitra)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = DaftarMitraAdapter(this, DaftarMitraList){

        }

    }

}