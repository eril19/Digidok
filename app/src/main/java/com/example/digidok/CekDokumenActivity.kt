package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CekDokumenActivity : AppCompatActivity() {

    var data:LaporanAsetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_dokumen)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("Cek Dokumen")

        val tutup = findViewById<TextView>(R.id.close_detail_btn)

        val CekDokumen = listOf<CekDokumenModel>(
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "01 - Dokumen PKS"
            ),
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "02 - Dokumen SK Gubernur / Sekretaris Daerah"
            ),
            CekDokumenModel(
                header_color = "Dikembalikan",
                nama_dokumen = "03 - Nota Dinas"
            ),
            CekDokumenModel(
                header_color = "Tidak ada dokumen",
                nama_dokumen = "04 - Surat Permohonan Mitra"
            ),
            CekDokumenModel(
                header_color = "Dikirim",
                nama_dokumen = "05 - Surat Penunjukan Kantor Jasa Penilai Publik (KJPP)"
            ),
            CekDokumenModel(
                header_color = "Draft",
                nama_dokumen = "06 - Surat Hasil Penilaian Kantor Jasa Penilai Publik (KJPP)"
            ),
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "07 - Notulen Rapat Dengan Mitra"
            ),
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "08 - Surat Tanda Setoran"
            ),
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "09 - Dokumen Pajak Bumi dan Bangunan (PBB)"
            ),
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "10 - Dokumen Asuransi"
            ),
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "11 - Dokumen Berita Acara Serah Terima (BAST)"
            ),
            CekDokumenModel(
                header_color = "Disetujui",
                nama_dokumen = "12 - Dokumen Pendukung Lainnya"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_laporan_aset)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = CekDokumenAdapter(this,  CekDokumen){

        }

        tutup.setOnClickListener {
            val i = Intent(this@CekDokumenActivity, RepositoriDokumenActivity::class.java)
            startActivity(i)
        }
    }
}