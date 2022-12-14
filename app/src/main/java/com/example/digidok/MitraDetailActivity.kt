package com.example.digidok

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.NPWPModel
import com.example.digidok.data.model.daftarMitraModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe
import org.w3c.dom.Text

class MitraDetailActivity : AppCompatActivity() {

//    var data: DaftarMitraModel? = null
    var hideButton: Boolean = false
    var npwpData = "-"
    var namaData = ""
    var alamatData = ""
    var kelurahanData = ""
    var kotaData = ""
    var kecamatanData = ""
    var provinsiData = ""
    var klasifikasiData = ""
    var kppData = "-"
    var kanwilData = ""
    var telpData = ""
    var faxData = ""
    var emailData = ""
    var ttlData = ""
    var tglDaftarData = ""
    var statusPKPData = ""
    var tglPKPData = ""
    var jenisPajakData = ""
    var badanHukumData = ""


    var isEdit: String = ""
    var NPWPcheck = ""
    var DataMitra: ArrayList<MitraDetailModel> = ArrayList()
    var npwp: EditText? = null
    var nama: EditText? = null
    var alamat: EditText? = null
    var kelurahan: EditText? = null
    var kecamatan: EditText? = null
    var kota: EditText? = null
    var provinsi: EditText? = null
    var klasifikasi: EditText? = null
    var kpp: EditText? = null
    var kanwil: EditText? = null
    var telp: EditText? = null
    var fax: EditText? = null
    var email: EditText? = null
    var ttl: EditText? = null
    var tgl_daftar: EditText? = null
    var status_pkp: EditText? = null
    var tgl_pkp: EditText? = null
    var jenis_pajak: EditText? = null
    var badan_hukum: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail)

        supportActionBar?.hide()


        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            onBackPressed()
            finish()
        }

        val backArrow = findViewById<ImageButton>(R.id.backArrowBtn)
        backArrow.setOnClickListener {
            onBackPressed()
            finish()
        }

//        data = intent.getParcelableExtra("daftarMitra")
        NPWPcheck = intent.getStringExtra("npwp") ?: ""
        isEdit = intent.getStringExtra("menu") ?: ""


        val switch = findViewById<Switch>(R.id.NPWPSwitch)
        val refresh = findViewById<ImageView>(R.id.refreshButton)

        if (!NPWPcheck.equals("")){
            getNpwp(NPWPcheck)
            npwp?.setText(NPWPcheck)
            npwpData = NPWPcheck
        }


        npwp = findViewById<EditText>(R.id.npwp)
        npwpData = npwp?.text.toString()
        nama = findViewById<EditText>(R.id.namadetailkjpp)
        namaData = nama?.text.toString()
        alamat = findViewById<EditText>(R.id.alamatdetail)
        alamatData = alamat?.text.toString()
        kelurahan = findViewById<EditText>(R.id.kelurahan)
        kelurahanData = kelurahan?.text.toString()
        kecamatan = findViewById<EditText>(R.id.kecamatan)
        kecamatanData = kecamatan?.text.toString()
        kota = findViewById<EditText>(R.id.kota)
        kotaData = kota?.text.toString()
        provinsi = findViewById<EditText>(R.id.provinsi)
        provinsiData = provinsi?.text.toString()
        klasifikasi = findViewById<EditText>(R.id.klu)
        klasifikasiData = klasifikasi?.text.toString()
        kpp = findViewById<EditText>(R.id.kpp)
        kppData = kpp?.text.toString()
        kanwil = findViewById<EditText>(R.id.kanwil)
        kanwilData = kanwil?.text.toString()
        telp = findViewById<EditText>(R.id.telp_mitra)
        telpData = telp?.text.toString()
        fax = findViewById<EditText>(R.id.fax_mitra)
        faxData = fax?.text.toString()
        email = findViewById<EditText>(R.id.email_mitra)
        emailData = email?.text.toString()
        ttl = findViewById<EditText>(R.id.ttl_mitra)
        ttlData = ttl?.text.toString()
        tgl_daftar = findViewById<EditText>(R.id.tgl_daftar_mitra)
        tglDaftarData = tgl_daftar?.text.toString()
        status_pkp = findViewById<EditText>(R.id.status_pkp_mitra)
        statusPKPData = status_pkp?.text.toString()
        tgl_pkp = findViewById<EditText>(R.id.tgl_pkp_mitra)
        tglPKPData = tgl_pkp?.text.toString()
        jenis_pajak = findViewById<EditText>(R.id.jenis_pajak_mitra)
        jenisPajakData = jenis_pajak?.text.toString()
        badan_hukum = findViewById<EditText>(R.id.badan_hukum_mitra)
        badanHukumData = badan_hukum?.text.toString()


        if (isEdit.equals("Edit", true) || isEdit.equals("Tambah", true)) {

            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (switch.isChecked) {
                    nama?.isEnabled = false
                    alamat?.isEnabled = false
                    kelurahan?.isEnabled = false
                    kecamatan?.isEnabled = false
                    kota?.isEnabled = false
                    provinsi?.isEnabled = false
                    klasifikasi?.isEnabled = false
                    kpp?.isEnabled = false
                    kanwil?.isEnabled = false
                    telp?.isEnabled = false
                    fax?.isEnabled = false
                    email?.isEnabled = false
                    ttl?.isEnabled = false
                    tgl_daftar?.isEnabled = false
                    status_pkp?.isEnabled = false
                    tgl_pkp?.isEnabled = false
                    jenis_pajak?.isEnabled = false
                    badan_hukum?.isEnabled = false

                    npwp?.isEnabled = true
                    npwp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    nama?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    alamat?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    kelurahan?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    kecamatan?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    kota?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    provinsi?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    klasifikasi?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    kpp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    kanwil?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    telp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    fax?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    email?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    ttl?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    tgl_daftar?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    status_pkp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    tgl_pkp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    jenis_pajak?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    badan_hukum?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                } else {

                    npwp?.isEnabled = false
                    npwp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
                    nama?.isEnabled = true
                    nama?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    alamat?.isEnabled = true
                    alamat?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    kelurahan?.isEnabled = true
                    kelurahan?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    kecamatan?.isEnabled = true
                    kecamatan?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    kota?.isEnabled = true
                    kota?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    provinsi?.isEnabled = true
                    provinsi?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    klasifikasi?.isEnabled = true
                    klasifikasi?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    kpp?.isEnabled = true
                    kpp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    kanwil?.isEnabled = true
                    kanwil?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    telp?.isEnabled = true
                    telp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    fax?.isEnabled = true
                    fax?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    email?.isEnabled = true
                    email?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    ttl?.isEnabled = true
                    ttl?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    tgl_daftar?.isEnabled = true
                    tgl_daftar?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    status_pkp?.isEnabled = true
                    status_pkp?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    tgl_pkp?.isEnabled = true
                    tgl_pkp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile_enable)
                    jenis_pajak?.isEnabled = true
                    jenis_pajak?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    badan_hukum?.isEnabled = true
                    badan_hukum?.background =
                        ContextCompat.getDrawable(
                            this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )


                }
            }

        }
        else {
            refresh.visibility = View.GONE
            switch.visibility = View.GONE
            npwp?.isEnabled = false
            npwp?.background = ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            nama?.isEnabled = false
            alamat?.isEnabled = false
            kelurahan?.isEnabled = false
            kecamatan?.isEnabled = false
            kota?.isEnabled = false
            provinsi?.isEnabled = false
            klasifikasi?.isEnabled = false
            kpp?.isEnabled = false
            kanwil?.isEnabled = false
            telp?.isEnabled = false
            fax?.isEnabled = false
            email?.isEnabled = false
            ttl?.isEnabled = false
            tgl_daftar?.isEnabled = false
            status_pkp?.isEnabled = false
            tgl_pkp?.isEnabled = false
            jenis_pajak?.isEnabled = false
            badan_hukum?.isEnabled = false
            nama?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            alamat?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            kelurahan?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            kecamatan?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            kota?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            provinsi?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            klasifikasi?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            kpp?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            kanwil?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            telp?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            fax?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            email?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            ttl?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            tgl_daftar?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            status_pkp?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            tgl_pkp?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            jenis_pajak?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
            badan_hukum?.background =
                ContextCompat.getDrawable(this@MitraDetailActivity, R.drawable.custom_profile)
        }

        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        next_detail_btn.setOnClickListener {
            val i = Intent(this@MitraDetailActivity, MitraDetailActivity2::class.java)
            if (isEdit.equals("Edit", true) || isEdit.equals("Tambah", true)) {
                i.putExtra("menu2", "Edit")
            } else {
                i.putExtra("menu2", "View")
            }
            startActivity(i)
        }

        refresh.setOnClickListener {
            getNpwp(npwp?.text.toString())
        }

    }

    fun getNpwp(noNpwp: String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getNPWP(
            token = Preferences.isToken(context = this@MitraDetailActivity),
            noNpwp = noNpwp,
            object : DataSource.NPWPCallback {
                override fun onSuccess(data: BaseApiModel<NPWPModel?>) {
                    isLoading = false
                    if (data.isSuccess) {

                        nama?.setText(data.data?.nama)
                        alamat?.setText(data.data?.alamat)
                        kelurahan?.setText(data.data?.kelurahan)
                        kecamatan?.setText(data.data?.kecamatan)
                        kota?.setText(data.data?.kabKota)
                        provinsi?.setText(data.data?.provinsi)
                        klasifikasi?.setText(data.data?.klasifikasiKlu)
//                        kpp.setText(data.data?.)
                        kanwil?.setText(data.data?.kanwil)
                        telp?.setText(data.data?.nomorTelepon)
                        fax?.setText(data.data?.nomorFax)
                        email?.setText(data.data?.email)
                        ttl?.setText(data.data?.ttl)
                        tgl_daftar?.setText(data.data?.tanggalDaftar)
                        status_pkp?.setText(data.data?.statusPkp)
                        tgl_pkp?.setText(data.data?.tanggalPengukuhanPkp)
                        jenis_pajak?.setText(data.data?.jenisWajibPajak)
                        badan_hukum?.setText(data.data?.badanHukum)

                        DataMitra.clear()
                        DataMitra?.add(
                            MitraDetailModel(
                                npwp = npwpData,
                                nama = namaData,
                                alamat = alamatData,
                                namaKpp = kppData,
                                kotaKabupaten = kotaData,
                                kecamatan = kecamatanData,
                                kelurahan = kelurahanData,
                                statusPkp = statusPKPData,
                                badanHukum = badanHukumData,
                                email = emailData,
                                kanwil = kanwilData,
                                provinsi = provinsiData,
                                klasifikasi = klasifikasiData,
                                jenisWajibPajak = jenisPajakData,
                                nomorTelepon = telpData,
                                nomorFax = faxData,
                                ttl = ttlData,
                                tanggalDaftar = tglDaftarData,
                                tanggalPengukuhanPkp = tglPKPData
                            )
                        )
                    }
                }

                override fun onError(message: String) {
                    Toast.makeText(this@MitraDetailActivity, "Data gagal dimuat", Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
                    Toast.makeText(this@MitraDetailActivity, "Data selesai dimuat", Toast.LENGTH_LONG).show()
                }

            })
    }
}