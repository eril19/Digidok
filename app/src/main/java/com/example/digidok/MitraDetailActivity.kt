package com.example.digidok

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MitraDetailActivity : AppCompatActivity() {

    var data: DaftarMitraModel? = null
    var hideButton : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail)

        supportActionBar?.hide()

        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        next_detail_btn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity, MitraDetailActivity2::class.java))
        }

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity, DaftarMitraActivity::class.java))
        }

        data = intent.getParcelableExtra("daftarMitra")
        hideButton = intent.getBooleanExtra("hideButton", false)
        val non_aktif_btn = findViewById<Button>(R.id.nonAktifBtn)
        non_aktif_btn.setOnClickListener {
            non_aktif_btn.setBackgroundColor(Color.RED)
        }

        /*non_aktif_btn dihide ketika hideButton bernilai true*/
        if(hideButton==false){
            non_aktif_btn.visibility = View.GONE
        } else {
            non_aktif_btn.visibility = View.VISIBLE
        }

        val npwp = findViewById<TextView>(R.id.npwp)
        val nama = findViewById<TextView>(R.id.namadetailkjpp)
        val alamat = findViewById<TextView>(R.id.alamatdetail)
        val kelurahan = findViewById<TextView>(R.id.kelurahan)
        val kecamatan = findViewById<TextView>(R.id.kecamatan)
        val kota = findViewById<TextView>(R.id.kota)
        val provinsi = findViewById<TextView>(R.id.provinsi)
        val klasifikasi = findViewById<TextView>(R.id.klu)
        val kpp = findViewById<TextView>(R.id.kpp)
        val kanwil = findViewById<TextView>(R.id.kanwil)
        val telp= findViewById<TextView>(R.id.telp_mitra)
        val fax = findViewById<TextView>(R.id.fax_mitra)
        val email = findViewById<TextView>(R.id.email_mitra)
        val ttl = findViewById<TextView>(R.id.ttl_mitra)
        val tgl_daftar = findViewById<TextView>(R.id.tgl_daftar_mitra)
        val status_pkp= findViewById<TextView>(R.id.status_pkp_mitra)
        val tgl_pkp = findViewById<TextView>(R.id.tgl_pkp_mitra)
        val jenis_pajak = findViewById<TextView>(R.id.jenis_pajak_mitra)
        val badan_hukum = findViewById<TextView>(R.id.badan_hukum_mitra)


        npwp.setText(data?.npwp)
        nama.setText(data?.nama_mitra)
//        alamat.setText(data?.alamat_mitra)
//        kelurahan.setText(data?.kelurahan_mitra)
//        kecamatan.setText(data?.kecamatan_mitra)
//        kota.setText(data?.kota_mitra)
//        provinsi.setText(data?.provinsi_mitra)
//        klasifikasi.setText(data?.klasifikasi_mitra)
//        kpp.setText(data?.kpp_mitra)
//        kanwil.setText(data?.kanwil_mitra)
//        telp.setText(data?.telp_mitra)
//        fax.setText(data?.fax_mitra)
//        email.setText(data?.email_mitra)
//        ttl.setText(data?.ttl_mitra)
//        tgl_daftar.setText(data?.tgl_daftar_mitra)
//        status_pkp.setText(data?.status_pkp_mitra)
//        tgl_pkp.setText(data?.tgl_pkp_mitra)
//        jenis_pajak.setText(data?.jeniw_pajak_mitra)
//        badan_hukum.setText(data?.badan_hukum_mitra)
    }
}