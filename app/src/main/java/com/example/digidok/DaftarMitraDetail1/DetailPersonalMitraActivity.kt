package com.example.digidok.DaftarMitraDetail1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.digidok.DaftarMitraDetail2.DetailKerjasamaMitraActivity
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

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
    var legalWp:Long  = 0
    var tahunGabung = ""
    var jenisMitra = ""
    var statusMitra = ""
    var idMitraCheck = ""
    var isTambah = ""
    var DataMitra: DetailPersonalMitraModel? = null
    var npwp: EditText? = null
    var namaKpp :EditText?=null
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

    lateinit var mDaftarMitraDetailViewModel: DetailPersonalMitraViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail)

        supportActionBar?.hide()

        mDaftarMitraDetailViewModel = ViewModelProvider(this@MitraDetailActivity).get(
            DetailPersonalMitraViewModel::class.java)
        mDaftarMitraDetailViewModel.token.value = Preferences.isToken(this@MitraDetailActivity)

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            onBackPressed()
            finish()
        }

        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        backArrow.setOnClickListener {
            onBackPressed()
            finish()
        }

        val header = findViewById<TextView>(R.id.header_title)

        isTambah = intent.getStringExtra("menu") ?: ""

        if(isTambah.equals("Tambah",true)){
            header.setText("Tambah Mitra")
        }
        else if(isTambah.equals("Edit",true)){
            header.setText("Edit Mitra")
        }
        else{
        header.setText("Detail Mitra")
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity, NotificationActivity::class.java))
        }


//        data = intent.getParcelableExtra("daftarMitra")
        idMitraCheck = intent.getStringExtra("id") ?: ""
        isEdit = intent.getStringExtra("menu") ?: ""


        val switch = findViewById<Switch>(R.id.NPWPSwitch)
        val refresh = findViewById<ImageView>(R.id.refreshButton)

        if (!idMitraCheck.equals("")){
            mDaftarMitraDetailViewModel.getDetailMitra(idMitraCheck)
            legalWp = 0
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
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    nama?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    alamat?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    kelurahan?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    kecamatan?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    kota?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    provinsi?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    klasifikasi?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    kpp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    kanwil?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    telp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    fax?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    email?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    ttl?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    tgl_daftar?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    status_pkp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    tgl_pkp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    jenis_pajak?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    badan_hukum?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                }
                else {

                    npwp?.isEnabled = false
                    npwp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile
                        )
                    nama?.isEnabled = true
                    nama?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    alamat?.isEnabled = true
                    alamat?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
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
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
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
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    kanwil?.isEnabled = true
                    kanwil?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    telp?.isEnabled = true
                    telp?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    fax?.isEnabled = true
                    fax?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    email?.isEnabled = true
                    email?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
                    ttl?.isEnabled = true
                    ttl?.background =
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
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
                        ContextCompat.getDrawable(this@MitraDetailActivity,
                            R.drawable.custom_profile_enable
                        )
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
            npwp?.background = ContextCompat.getDrawable(this@MitraDetailActivity,
                R.drawable.custom_profile
            )
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

        observeViewModel()

        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        next_detail_btn.setOnClickListener {
            val i = Intent(this@MitraDetailActivity, DetailKerjasamaMitraActivity::class.java)

            DataMitra = DetailPersonalMitraModel(
                    npwp = npwp?.text.toString(),
                    nama = nama?.text.toString(),
                    alamat = alamat?.text.toString(),
                    namaKpp = namaKpp?.text.toString(),
                    kotaKabupaten = kota?.text.toString(),
                    kecamatan = kecamatan?.text.toString(),
                    kelurahan = kelurahan?.text.toString(),
                    statusPkp = status_pkp?.text.toString(),
                    badanHukum = badan_hukum?.text.toString(),
                    email = email?.text.toString(),
                    kanwil = kanwil?.text.toString(),
                    provinsi = provinsi?.text.toString(),
                    klasifikasi = klasifikasi?.text.toString(),
                    jenisWajibPajak = jenis_pajak?.text.toString(),
                    nomorTelepon = telp?.text.toString(),
                    nomorFax = fax?.text.toString(),
                    ttl = ttl?.text.toString(),
                    legalWp = legalWp,
                    statusMitra = statusMitra,
                    jenisMitra = jenisMitra,
                    tahunGabung = tahunGabung,
                    tanggalDaftar = tgl_daftar?.text.toString(),
                    tanggalPengukuhanPkp = tgl_pkp?.text.toString()
            )
            if (isEdit.equals("Edit", true)) {
                i.putExtra("dataDetail", DataMitra)
                i.putExtra("menu2", "Edit")
                i.putExtra("id",idMitraCheck)

            }
            else if (isEdit.equals("Tambah", true)){
                i.putExtra("dataDetail", DataMitra)
                i.putExtra("menu2", "Tambah")
            }
            else {
                i.putExtra("dataDetail", DataMitra)
                i.putExtra("menu2", "View")
            }
            startActivity(i)
        }

        refresh.setOnClickListener {
            mDaftarMitraDetailViewModel.getNpwp(npwp?.text.toString())
            legalWp = 1
        }

    }

    private fun observeViewModel() {
        mDaftarMitraDetailViewModel.mMessageResponse.observe(this){
            Toast.makeText(this@MitraDetailActivity, it, Toast.LENGTH_LONG).show()
        }

        mDaftarMitraDetailViewModel.npwp.observe(this){
            npwp?.setText(it)
        }

        mDaftarMitraDetailViewModel.nama.observe(this){
            npwp?.setText(mDaftarMitraDetailViewModel.npwp.value)
            nama?.setText(mDaftarMitraDetailViewModel.nama.value)
        }
        mDaftarMitraDetailViewModel.alamat.observe(this){
            alamat?.setText(mDaftarMitraDetailViewModel.alamat.value)
        }
        mDaftarMitraDetailViewModel.kecamatan.observe(this){
            kecamatan?.setText(mDaftarMitraDetailViewModel.kecamatan.value)
        }
        mDaftarMitraDetailViewModel.kelurahan.observe(this){
            kelurahan?.setText(mDaftarMitraDetailViewModel.kelurahan.value)
        }
        mDaftarMitraDetailViewModel.kota.observe(this){
            kota?.setText(mDaftarMitraDetailViewModel.kota.value)
        }
        mDaftarMitraDetailViewModel.provinsi.observe(this){
            provinsi?.setText(mDaftarMitraDetailViewModel.provinsi.value)
        }
        mDaftarMitraDetailViewModel.klasifikasi.observe(this){
            klasifikasi?.setText(mDaftarMitraDetailViewModel.klasifikasi.value)
        }
        mDaftarMitraDetailViewModel.kpp.observe(this){
            kpp?.setText(mDaftarMitraDetailViewModel.kpp.value)
        }
        mDaftarMitraDetailViewModel.kanwil.observe(this){
            kanwil?.setText(mDaftarMitraDetailViewModel.kanwil.value)
        }
        mDaftarMitraDetailViewModel.telp.observe(this){
            telp?.setText(mDaftarMitraDetailViewModel.telp.value)
        }
        mDaftarMitraDetailViewModel.fax.observe(this){
            fax?.setText(mDaftarMitraDetailViewModel.fax.value)
        }
        mDaftarMitraDetailViewModel.email.observe(this){
            email?.setText(mDaftarMitraDetailViewModel.email.value)
        }
        mDaftarMitraDetailViewModel.ttl.observe(this){
            ttl?.setText(mDaftarMitraDetailViewModel.ttl.value)
        }
        mDaftarMitraDetailViewModel.tgl_daftar.observe(this){
            tgl_daftar?.setText(mDaftarMitraDetailViewModel.tgl_daftar.value)
        }
        mDaftarMitraDetailViewModel.status_pkp.observe(this){
            status_pkp?.setText(mDaftarMitraDetailViewModel.status_pkp.value)
        }
        mDaftarMitraDetailViewModel.tgl_pkp.observe(this){
            tgl_pkp?.setText(mDaftarMitraDetailViewModel.tgl_pkp.value)
        }
        mDaftarMitraDetailViewModel.jenis_pajak.observe(this){
            jenis_pajak?.setText(mDaftarMitraDetailViewModel.jenis_pajak.value)
        }
        mDaftarMitraDetailViewModel.statusMitra.observe(this){
            statusMitra = mDaftarMitraDetailViewModel.statusMitra.value.safe()
         }
        mDaftarMitraDetailViewModel.jenisMitra.observe(this){
            jenisMitra = mDaftarMitraDetailViewModel.jenisMitra.value.safe()
        }
        mDaftarMitraDetailViewModel.tahunGabung.observe(this){
            tahunGabung = mDaftarMitraDetailViewModel.tahunGabung.value.safe()
            legalWp = mDaftarMitraDetailViewModel.legalWp.value.toString().toLong()
        }
        mDaftarMitraDetailViewModel.legalWp.observe(this){
            legalWp = mDaftarMitraDetailViewModel.legalWp.value.toString().toLong()
        }
    }

}