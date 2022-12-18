package com.example.digidok

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class MitraDetailActivity2 : AppCompatActivity() {

    var npwp = "-"
    var nama = ""
    var alamat = ""
    var kelurahan = ""
    var kota = ""
    var kecamatan = ""
    var provinsi = ""
    var klasifikasi = ""
    var kpp = "-"
    var kanwil = ""
    var telp = ""
    var fax = ""
    var email = ""
    var ttl = ""
    var tglDaftar = ""
    var statusPKP = ""
    var tglPKP = ""
    var jenisPajak = ""
    var badanHukum = ""
    var jenisMitra = ""
    var statusMitra = ""
    var companyProfile = ""
    var tahungabung: EditText? = null
    var tahun = 0
    var spinner_jenis_mitra: Spinner? = null
    val listJenisMitra =
        arrayListOf("Perorangan", "BUMD", "BUMN", "Perusahaan Swasta", "Yayasan / Foundation")
    var isEdit: String = ""
    var spinner_status_mitra: Spinner? = null
    val listStatusMitra = arrayListOf("Whitelist", "Redlist", "Blacklist")
    var data: MitraDetailModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail2)

        supportActionBar?.hide()
        isEdit = intent.getStringExtra("menu2") ?: ""
        data = intent.getParcelableExtra("dataDetail")
        spinner_jenis_mitra = findViewById<Spinner>(R.id.spinner_jenis_mitra)
        spinner_jenis_mitra?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    if (position - 1 == 0) {
                        jenisMitra = "001"
                    } else if (position - 1 == 1) {
                        jenisMitra = "002"
                    } else if (position - 1 == 2) {
                        jenisMitra = "003"
                    } else if (position - 1 == 3) {
                        jenisMitra = "004"
                    } else if (position - 1 == 4) {
                        jenisMitra = "005"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinner_status_mitra = findViewById<Spinner>(R.id.spinner_status_mitra)
        spinner_status_mitra?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    if (position - 1 == 0) {
                        statusMitra = "001"
                    } else if (position - 1 == 1) {
                        statusMitra = "002"
                    } else if (position - 1 == 2) {
                        statusMitra = "003"
                    }

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
            onBackPressed()
        }

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, DaftarMitraActivity::class.java))
            finish()
        }

        npwp = data?.npwp.toString()
        nama = data?.nama.toString()
        alamat = data?.alamat.toString()
        kelurahan = data?.kelurahan.toString()
        kota = data?.kotaKabupaten.toString()
        kecamatan = data?.kecamatan.toString()
        provinsi = data?.provinsi.toString()
        klasifikasi = data?.klasifikasi.toString()
        kpp = data?.namaKpp.toString()
        kanwil = data?.kanwil.toString()
        telp = data?.nomorTelepon.toString()
        fax = data?.nomorFax.toString()
        email = data?.email.toString()
        ttl = data?.ttl.toString()
        tglDaftar = data?.tanggalDaftar.toString()
        statusPKP = data?.statusPkp.toString()
        tglPKP = data?.tanggalPengukuhanPkp.toString()
        jenisPajak = data?.jenisWajibPajak.toString()
        badanHukum = data?.badanHukum.toString()


//        tahun = Integer.parseInt(tahungabung?.text.toString())

        val simpan = findViewById<Button>(R.id.save_detail_mitra_btn)
        simpan.setOnClickListener {
            InsertData(
                npwp,
                nama,
                alamat,
                kelurahan,
                kecamatan,
                kota,
                provinsi,
                klasifikasi,
                kpp,
                kanwil,
                telp,
                fax,
                email,
                ttl,
                tglDaftar,
                statusPKP,
                tglPKP,
                jenisPajak,
                badanHukum,
                tahunGabung = 2022,
                jenisMitra,
                statusMitra,
                companyProfile
            )
            startActivity(Intent(this@MitraDetailActivity2, DaftarMitraActivity::class.java))
        }

        val tahunGabung = findViewById<EditText>(R.id.tahun_gabung)
        val jenisMitra = findViewById<Spinner>(R.id.spinner_jenis_mitra)
        val statusMitra = findViewById<Spinner>(R.id.spinner_status_mitra)

        if (isEdit.equals("View", true)) {
            simpan.visibility = View.GONE
            tahunGabung.isEnabled = false
            tahunGabung.background =
                ContextCompat.getDrawable(tahunGabung.context, R.drawable.custom_profile)
            jenisMitra.isClickable = false
            jenisMitra.isEnabled = false
            jenisMitra.background =
                ContextCompat.getDrawable(jenisMitra.context, R.drawable.custom_profile)
            statusMitra.isClickable = false
            statusMitra.isEnabled = false
            statusMitra.background =
                ContextCompat.getDrawable(statusMitra.context, R.drawable.custom_profile)
        }

        setSpinnerKategori()
    }


    fun InsertData(
        npwp: String,
        nama: String,
        alamat: String,
        kelurahan: String,
        kecamatan: String,
        kotaKabupaten: String,
        provinsi: String,
        klasifikasi: String,
        namaKpp: String,
        kanwil: String,
        nomorTelepon: String,
        nomorFax: String,
        email: String,
        ttl: String,
        tanggalDaftar: String,
        statusPkp: String,
        tanggalPengukuhanPkp: String,
        jenisWajibPajak: String,
        badanHukum: String,
        tahunGabung: Int,
        jenisMitra: String,
        statusMitra: String,
        companyProfile: String
    ) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.InsertMitra(
            token = Preferences.isToken(context = this@MitraDetailActivity2),
            npwp = npwp,
            nama = nama,
            alamat = alamat,
            kelurahan = kelurahan,
            kecamatan = kecamatan,
            kotaKabupaten = kotaKabupaten,
            provinsi = provinsi,
            klasifikasi = klasifikasi,
            namaKpp = namaKpp,
            kanwil = kanwil,
            nomorTelepon = nomorTelepon,
            nomorFax = nomorFax,
            email = email,
            ttl = ttl,
            tanggalDaftar = tanggalDaftar,
            statusPkp = statusPkp,
            tanggalPengukuhanPkp = tanggalPengukuhanPkp,
            jenisWajibPajak = jenisWajibPajak,
            badanHukum = badanHukum,
            tahunGabung = tahunGabung,
            jenisMitra = jenisMitra,
            statusMitra = statusMitra,
            companyProfile = companyProfile,
            object : DataSource.InsertMitraCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading = false
                    if (data.isSuccess) {

                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                }

                override fun onFinish() {
                    isLoading = false
                }

            })
    }

    fun setSpinnerKategori() {
        val arrayStringTahun = arrayListOf("Pilih Jenis Mitra")
        arrayStringTahun.addAll(listJenisMitra)
        spinner_jenis_mitra?.adapter =
            object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringTahun) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    return if (convertView != null) {
                        if (convertView is TextView) {
                            if (position == 0) convertView.setTextColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.black
                                )
                            )
                            try {
                                convertView.typeface = Typeface.createFromAsset(
                                    convertView.context.resources.assets,
                                    "fonts/cs.ttf"
                                )
                            } catch (e: Exception) {
                                showErrorInflateFont()
                            }
                            convertView
                        } else {
                            convertView
                        }
                    } else {
                        super.getView(position, convertView, parent)
                    }
                }
            }

        val arrayStringWilayah = arrayListOf("Pilih Status")
        arrayStringWilayah.addAll(listStatusMitra)
        spinner_status_mitra?.adapter =
            object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringWilayah) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    return if (convertView != null) {
                        if (convertView is TextView) {
                            if (position == 0) convertView.setTextColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.black
                                )
                            )
                            try {
                                convertView.typeface = Typeface.createFromAsset(
                                    convertView.context.resources.assets,
                                    "fonts/cs.ttf"
                                )
                            } catch (e: Exception) {
                                showErrorInflateFont()
                            }
                            convertView
                        } else {
                            convertView
                        }
                    } else {
                        super.getView(position, convertView, parent)
                    }
                }
            }

    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")
}