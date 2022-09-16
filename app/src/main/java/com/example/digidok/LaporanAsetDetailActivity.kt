package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LaporanAsetDetailActivity : AppCompatActivity() {

    var data:LaporanAsetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_aset_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("laporanAset")

        val tutup = findViewById<TextView>(R.id.close_detail_btn)
        val id_pks = findViewById<TextView>(R.id.nomerpks)
        val nama_mitra = findViewById<TextView>(R.id.namamitra)
        val nilai_pks = findViewById<TextView>(R.id.hargapks)
        val jenis_kerjasama = findViewById<TextView>(R.id.jeniskerjasama)

        val LaporanAsetDetail = listOf<LaporanAsetDetailModel>(
            LaporanAsetDetailModel(
                num_laporan = "#1",
                kode_barang = "131010104001::000001",
                nama_bmd = "Tanah Bangunan Kantor Pemerintah",
                kode_lokasi = "00392",
                nama_lokasi = "PUSAT PENYIMPANAN BARANG DAERAH",
                luas_bmd = "15855 M2",
                keterangan_bmd = "Jl. Perintis Kemerdekaan No. 1"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_laporan_aset)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = LaporanAsetDetailAdapter(this,  LaporanAsetDetail){

        }

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