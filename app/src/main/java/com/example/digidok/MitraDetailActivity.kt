package com.example.digidok

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MitraDetailActivity : AppCompatActivity() {

    var data: DaftarMitraModel? = null
    var hideButton: Boolean = false
    var isEdit: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail)

        supportActionBar?.hide()



        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
//            startActivity(Intent(this@MitraDetailActivity, DaftarMitraActivity::class.java))
//            finish()
            onBackPressed()
            finish()
        }

        data = intent.getParcelableExtra("daftarMitra")
        isEdit = intent.getStringExtra("menu") ?: ""


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
        val telp = findViewById<TextView>(R.id.telp_mitra)
        val fax = findViewById<TextView>(R.id.fax_mitra)
        val email = findViewById<TextView>(R.id.email_mitra)
        val ttl = findViewById<TextView>(R.id.ttl_mitra)
        val tgl_daftar = findViewById<TextView>(R.id.tgl_daftar_mitra)
        val status_pkp = findViewById<TextView>(R.id.status_pkp_mitra)
        val tgl_pkp = findViewById<TextView>(R.id.tgl_pkp_mitra)
        val jenis_pajak = findViewById<TextView>(R.id.jenis_pajak_mitra)
        val badan_hukum = findViewById<TextView>(R.id.badan_hukum_mitra)
        var switch = findViewById<Switch>(R.id.NPWPSwitch)
        var refresh = findViewById<ImageView>(R.id.refreshButton)

        if (isEdit.equals("Edit", true) || isEdit.equals("Tambah", true)) {

            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (switch.isChecked) {
                    nama.isEnabled = false
                    alamat.isEnabled = false
                    kelurahan.isEnabled = false
                    kecamatan.isEnabled = false
                    kota.isEnabled = false
                    provinsi.isEnabled = false
                    klasifikasi.isEnabled = false
                    kpp.isEnabled = false
                    kanwil.isEnabled = false
                    telp.isEnabled = false
                    fax.isEnabled = false
                    email.isEnabled = false
                    ttl.isEnabled = false
                    tgl_daftar.isEnabled = false
                    status_pkp.isEnabled = false
                    tgl_pkp.isEnabled = false
                    jenis_pajak.isEnabled = false
                    badan_hukum.isEnabled = false

                    npwp.isEnabled = true
                    npwp.background = ContextCompat.getDrawable(nama.context, R.drawable.custom_profile_enable)
                    nama.background =
                        ContextCompat.getDrawable(nama.context, R.drawable.custom_profile)
                    alamat.background =
                        ContextCompat.getDrawable(alamat.context, R.drawable.custom_profile)
                    kelurahan.background =
                        ContextCompat.getDrawable(kelurahan.context, R.drawable.custom_profile)
                    kecamatan.background =
                        ContextCompat.getDrawable(kecamatan.context, R.drawable.custom_profile)
                    kota.background =
                        ContextCompat.getDrawable(kota.context, R.drawable.custom_profile)
                    provinsi.background =
                        ContextCompat.getDrawable(provinsi.context, R.drawable.custom_profile)
                    klasifikasi.background =
                        ContextCompat.getDrawable(klasifikasi.context, R.drawable.custom_profile)
                    kpp.background =
                        ContextCompat.getDrawable(kpp.context, R.drawable.custom_profile)
                    kanwil.background =
                        ContextCompat.getDrawable(kanwil.context, R.drawable.custom_profile)
                    telp.background =
                        ContextCompat.getDrawable(telp.context, R.drawable.custom_profile)
                    fax.background =
                        ContextCompat.getDrawable(fax.context, R.drawable.custom_profile)
                    email.background =
                        ContextCompat.getDrawable(email.context, R.drawable.custom_profile)
                    ttl.background =
                        ContextCompat.getDrawable(ttl.context, R.drawable.custom_profile)
                    tgl_daftar.background =
                        ContextCompat.getDrawable(tgl_daftar.context, R.drawable.custom_profile)
                    status_pkp.background =
                        ContextCompat.getDrawable(status_pkp.context, R.drawable.custom_profile)
                    tgl_pkp.background =
                        ContextCompat.getDrawable(tgl_pkp.context, R.drawable.custom_profile)
                    jenis_pajak.background =
                        ContextCompat.getDrawable(jenis_pajak.context, R.drawable.custom_profile)
                    badan_hukum.background =
                        ContextCompat.getDrawable(badan_hukum.context, R.drawable.custom_profile)
                } else {

                    npwp.isEnabled = false
                    npwp.background = ContextCompat.getDrawable(npwp.context, R.drawable.custom_profile)
                    nama.isEnabled = true
                    nama.background =
                        ContextCompat.getDrawable(nama.context, R.drawable.custom_profile_enable)
                    alamat.isEnabled = true
                    alamat.background =
                        ContextCompat.getDrawable(alamat.context, R.drawable.custom_profile_enable)
                    kelurahan.isEnabled = true
                    kelurahan.background =
                        ContextCompat.getDrawable(kelurahan.context, R.drawable.custom_profile_enable)
                    kecamatan.isEnabled = true
                    kecamatan.background =
                        ContextCompat.getDrawable(kecamatan.context, R.drawable.custom_profile_enable)
                    kota.isEnabled = true
                    kota.background =
                        ContextCompat.getDrawable(kota.context, R.drawable.custom_profile_enable)
                    provinsi.isEnabled = true
                    provinsi.background =
                        ContextCompat.getDrawable(provinsi.context, R.drawable.custom_profile_enable)
                    klasifikasi.isEnabled = true
                    klasifikasi.background =
                        ContextCompat.getDrawable(klasifikasi.context, R.drawable.custom_profile_enable)
                    kpp.isEnabled = true
                    kpp.background =
                        ContextCompat.getDrawable(kpp.context, R.drawable.custom_profile_enable)
                    kanwil.isEnabled = true
                    kanwil.background =
                        ContextCompat.getDrawable(kanwil.context, R.drawable.custom_profile_enable)
                    telp.isEnabled = true
                    telp.background =
                        ContextCompat.getDrawable(telp.context, R.drawable.custom_profile_enable)
                    fax.isEnabled = true
                    fax.background =
                        ContextCompat.getDrawable(fax.context, R.drawable.custom_profile_enable)
                    email.isEnabled = true
                    email.background =
                        ContextCompat.getDrawable(email.context, R.drawable.custom_profile_enable)
                    ttl.isEnabled = true
                    ttl.background =
                        ContextCompat.getDrawable(ttl.context, R.drawable.custom_profile_enable)
                    tgl_daftar.isEnabled = true
                    tgl_daftar.background =
                        ContextCompat.getDrawable(tgl_daftar.context, R.drawable.custom_profile_enable)
                    status_pkp.isEnabled = true
                    status_pkp.background =
                        ContextCompat.getDrawable(status_pkp.context, R.drawable.custom_profile_enable)
                    tgl_pkp.isEnabled = true
                    tgl_pkp.background =
                        ContextCompat.getDrawable(tgl_pkp.context, R.drawable.custom_profile_enable)
                    jenis_pajak.isEnabled = true
                    jenis_pajak.background =
                        ContextCompat.getDrawable(jenis_pajak.context, R.drawable.custom_profile_enable)
                    badan_hukum.isEnabled = true
                    badan_hukum.background =
                        ContextCompat.getDrawable(badan_hukum.context, R.drawable.custom_profile_enable)


                }
            }

        } else {
            refresh.visibility = View.GONE
            switch.visibility = View.GONE
            npwp.isEnabled = false
            npwp.background = ContextCompat.getDrawable(npwp.context, R.drawable.custom_profile)
            nama.isEnabled = false
            alamat.isEnabled = false
            kelurahan.isEnabled = false
            kecamatan.isEnabled = false
            kota.isEnabled = false
            provinsi.isEnabled = false
            klasifikasi.isEnabled = false
            kpp.isEnabled = false
            kanwil.isEnabled = false
            telp.isEnabled = false
            fax.isEnabled = false
            email.isEnabled = false
            ttl.isEnabled = false
            tgl_daftar.isEnabled = false
            status_pkp.isEnabled = false
            tgl_pkp.isEnabled = false
            jenis_pajak.isEnabled = false
            badan_hukum.isEnabled = false
            nama.background =
                ContextCompat.getDrawable(nama.context, R.drawable.custom_profile)
            alamat.background =
                ContextCompat.getDrawable(alamat.context, R.drawable.custom_profile)
            kelurahan.background =
                ContextCompat.getDrawable(kelurahan.context, R.drawable.custom_profile)
            kecamatan.background =
                ContextCompat.getDrawable(kecamatan.context, R.drawable.custom_profile)
            kota.background =
                ContextCompat.getDrawable(kota.context, R.drawable.custom_profile)
            provinsi.background =
                ContextCompat.getDrawable(provinsi.context, R.drawable.custom_profile)
            klasifikasi.background =
                ContextCompat.getDrawable(klasifikasi.context, R.drawable.custom_profile)
            kpp.background =
                ContextCompat.getDrawable(kpp.context, R.drawable.custom_profile)
            kanwil.background =
                ContextCompat.getDrawable(kanwil.context, R.drawable.custom_profile)
            telp.background =
                ContextCompat.getDrawable(telp.context, R.drawable.custom_profile)
            fax.background =
                ContextCompat.getDrawable(fax.context, R.drawable.custom_profile)
            email.background =
                ContextCompat.getDrawable(email.context, R.drawable.custom_profile)
            ttl.background =
                ContextCompat.getDrawable(ttl.context, R.drawable.custom_profile)
            tgl_daftar.background =
                ContextCompat.getDrawable(tgl_daftar.context, R.drawable.custom_profile)
            status_pkp.background =
                ContextCompat.getDrawable(status_pkp.context, R.drawable.custom_profile)
            tgl_pkp.background =
                ContextCompat.getDrawable(tgl_pkp.context, R.drawable.custom_profile)
            jenis_pajak.background =
                ContextCompat.getDrawable(jenis_pajak.context, R.drawable.custom_profile)
            badan_hukum.background =
                ContextCompat.getDrawable(badan_hukum.context, R.drawable.custom_profile)
        }

        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        next_detail_btn.setOnClickListener {
            val i = Intent(this@MitraDetailActivity, MitraDetailActivity2::class.java)
            if(isEdit.equals("Edit",true)||isEdit.equals("Tambah",true)){
                i.putExtra("menu2", "Edit")
            }
            else{
                i.putExtra("menu2", "View")
            }
            startActivity(i)
        }

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