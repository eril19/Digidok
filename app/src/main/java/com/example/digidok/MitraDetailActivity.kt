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
    var isEdit: String = ""
    var NPWPcheck = ""

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
//            startActivity(Intent(this@MitraDetailActivity, DaftarMitraActivity::class.java))
//            finish()
            onBackPressed()
            finish()
        }

//        data = intent.getParcelableExtra("daftarMitra")
        NPWPcheck = intent.getStringExtra("npwp") ?: ""
        isEdit = intent.getStringExtra("menu") ?: ""


        npwp = findViewById<EditText>(R.id.npwp)
        nama = findViewById<EditText>(R.id.namadetailkjpp)
        alamat = findViewById<EditText>(R.id.alamatdetail)
        kelurahan = findViewById<EditText>(R.id.kelurahan)
        kecamatan = findViewById<EditText>(R.id.kecamatan)
        kota = findViewById<EditText>(R.id.kota)
        provinsi = findViewById<EditText>(R.id.provinsi)
        klasifikasi = findViewById<EditText>(R.id.klu)
        kpp = findViewById<EditText>(R.id.kpp)
        kanwil = findViewById<EditText>(R.id.kanwil)
        telp = findViewById<EditText>(R.id.telp_mitra)
        fax = findViewById<EditText>(R.id.fax_mitra)
        email = findViewById<EditText>(R.id.email_mitra)
        ttl = findViewById<EditText>(R.id.ttl_mitra)
        tgl_daftar = findViewById<EditText>(R.id.tgl_daftar_mitra)
        status_pkp = findViewById<EditText>(R.id.status_pkp_mitra)
        tgl_pkp = findViewById<EditText>(R.id.tgl_pkp_mitra)
        jenis_pajak = findViewById<EditText>(R.id.jenis_pajak_mitra)
        badan_hukum = findViewById<EditText>(R.id.badan_hukum_mitra)

        val switch = findViewById<Switch>(R.id.NPWPSwitch)
        val refresh = findViewById<ImageView>(R.id.refreshButton)

        if (!NPWPcheck.equals("")){
            getNpwp(NPWPcheck)
            npwp?.setText(NPWPcheck)
        }

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

        } else {
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