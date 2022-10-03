package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

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

        val nomerPengajuan = findViewById<TextView>(R.id.no_pengajuan)
        val namaMitra = findViewById<TextView>(R.id.nama_mitra)
        val skemaPemanfaatan = findViewById<TextView>(R.id.skema_pemanfaatan)
        val tujuan = findViewById<TextView>(R.id.tujuan)
        val noSurat = findViewById<TextView>(R.id.no_surat)
        val tglSurat = findViewById<TextView>(R.id.tgl_surat)
        val objek = findViewById<TextView>(R.id.objek)
        val nilai = findViewById<TextView>(R.id.nilai)
        val tglMulai = findViewById<TextView>(R.id.tgl_mulai)
        val tglAkhir = findViewById<TextView>(R.id.tgl_akhir)
        val perihal = findViewById<TextView>(R.id.perihal)

        val PengajuanKerjasamaDetail = listOf<PengajuanKerjasamaDetailModel>(
            PengajuanKerjasamaDetailModel(
                kodeLokasi = "0089879000",
                namaLokasi  = "Metro Jaya",
                kodeBarang  = "0009-0989-0087",
                namaBarang  = "Rumah",
                alamat  = "JL. Kundur Jaya",
                Luas  = "2000m2",
                LuasManfaat  = "1500m2"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = PengajuanKerjasamaDetailAdapter(this, PengajuanKerjasamaDetail, object:PengajuanKerjasamaDetailAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }

        }){

        }


        data = intent.getParcelableExtra("PengajuanKerjasama")
//        samain sama i.putExtra!


        nomerPengajuan.text = data?.noPengajuan
        nilai.text = data?.nilai
        namaMitra.text = data?.nama_mitra
        skemaPemanfaatan.text = data?.skemaPemanfaatan
        tujuan.text = data?.tujuan
        noSurat.text= data?.noSurat
        tglSurat.text = data?.tglSurat
        tglAkhir.text = data?.tglAkhir
        tglMulai.text = data?.tglAkhir
        objek.text = data?.objek
        perihal.text = data?.perihal
    }
}