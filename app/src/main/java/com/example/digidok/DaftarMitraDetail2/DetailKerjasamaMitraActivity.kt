package com.example.digidok.DaftarMitraDetail2

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.digidok.DaftarMitra.DaftarMitraActivity
import com.example.digidok.DaftarMitraDetail1.DetailPersonalMitraModel
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.SpinnerModel.JenisMitraModel
import com.example.digidok.SpinnerModel.StatusMitraModel
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DetailKerjasamaMitraActivity : AppCompatActivity() {

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
    var tahunGabungValue = ""
    var legalWp:Long = 0
    var tahun = 0
    var spinner_jenis_mitra: Spinner? = null
    val listJenisMitra : ArrayList<JenisMitraModel> = ArrayList()
    var isEdit: String = ""
    var isTambah = ""
    var idMitraCheck : String = ""
    var spinner_status_mitra: Spinner? = null
    val listStatusMitra : ArrayList<StatusMitraModel> = ArrayList()
    var data: DetailPersonalMitraModel? = null

    var profilPT : TextView ?= null
    var pdfPic : ImageView ? = null
    var tnc : TextView?=null

    lateinit var mDaftarMitraDetailViewModel2: DetailKerjasamaMitraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail2)

        supportActionBar?.hide()
        isEdit = intent.getStringExtra("menu2") ?: ""
        data = intent.getParcelableExtra("dataDetail")
        idMitraCheck = intent.getStringExtra("id") ?: ""

        mDaftarMitraDetailViewModel2 = ViewModelProvider(this@DetailKerjasamaMitraActivity).get(
            DetailKerjasamaMitraViewModel::class.java)
        mDaftarMitraDetailViewModel2.token.value = Preferences.isToken(this@DetailKerjasamaMitraActivity)

        spinner_jenis_mitra = findViewById<Spinner>(R.id.spinner_jenis_mitra)
        spinner_jenis_mitra?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(listJenisMitra.size != 0){
                    if (position != 0) {
                        jenisMitra = listJenisMitra[position-1].value.safe()
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
                if(listStatusMitra.size != 0){
                    if (position != 0) {
                        statusMitra = listStatusMitra[position-1].value.safe()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        mDaftarMitraDetailViewModel2.getJenisMitra()
        mDaftarMitraDetailViewModel2.getStatusMitra()

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
            onBackPressed()
        }

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@DetailKerjasamaMitraActivity, DaftarMitraActivity::class.java))
            finish()
        }

        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        backArrow.setOnClickListener {
            onBackPressed()
        }

        val header = findViewById<TextView>(R.id.header_title)

        isTambah = intent.getStringExtra("menu2") ?: ""

        if(isTambah.equals("Tambah",true)){
            header.setText("Tambah Mitra")
            profilPT = findViewById(R.id.title_profil_perusahaan)
            pdfPic = findViewById(R.id.gambarpdf)
            tnc = findViewById(R.id.pdfcapt)

            profilPT?.visibility = View.GONE
            pdfPic?.visibility = View.GONE
            tnc?.visibility = View.GONE
        }
        else if(isTambah.equals("Edit",true)){
            header.setText("Edit Mitra")
            profilPT = findViewById(R.id.title_profil_perusahaan)
            pdfPic = findViewById(R.id.gambarpdf)
            tnc = findViewById(R.id.pdfcapt)

            profilPT?.visibility = View.GONE
            pdfPic?.visibility = View.GONE
            tnc?.visibility = View.GONE
        }
        else{
            header.setText("Detail Mitra")
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@DetailKerjasamaMitraActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@DetailKerjasamaMitraActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@DetailKerjasamaMitraActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DetailKerjasamaMitraActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DetailKerjasamaMitraActivity, NotificationActivity::class.java))
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
        legalWp = data?.legalWp?:0
        tahunGabungValue = data?.tahunGabung.toString()

        jenisMitra = data?.jenisMitra.safe()
        statusMitra = data?.statusMitra.safe()


//        tahun = Integer.parseInt(tahungabung?.text.toString())

        val etTahunGabung = findViewById<EditText>(R.id.tahun_gabung)
        val etJenisMitra = findViewById<Spinner>(R.id.spinner_jenis_mitra)
        val etStatusMitra = findViewById<Spinner>(R.id.spinner_status_mitra)
        val etSimpan = findViewById<Button>(R.id.save_detail_mitra_btn)

        if (isEdit.equals("Edit")){
            etSimpan.setOnClickListener {
                tahunGabungValue = etTahunGabung.text.toString()
                mDaftarMitraDetailViewModel2.UpdateData(
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
                    tahunGabungValue,
                    jenisMitra,
                    statusMitra,
                    legalWp,
                    companyProfile,
                    idMitraCheck
                )
                startActivity(Intent(this@DetailKerjasamaMitraActivity, DaftarMitraActivity::class.java))
            }

        }
        else{
            etSimpan.setOnClickListener {

                tahunGabungValue = etTahunGabung.text.toString()
                mDaftarMitraDetailViewModel2.InsertData(
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
                    tahunGabungValue,
                    jenisMitra,
                    statusMitra,
                    legalWp,
                    companyProfile
                )
                startActivity(Intent(this@DetailKerjasamaMitraActivity, DaftarMitraActivity::class.java))
            }
        }


        etTahunGabung.setText(tahunGabungValue)
        
        if (isEdit.equals("View", true)) {
            etSimpan.visibility = View.GONE
            etTahunGabung.isEnabled = false
            etTahunGabung.background =
                ContextCompat.getDrawable(etTahunGabung.context, R.drawable.custom_profile)
            etJenisMitra.isClickable = false
            etJenisMitra.isEnabled = false
            etJenisMitra.background =
                ContextCompat.getDrawable(etJenisMitra.context, R.drawable.custom_profile)
            etStatusMitra.isClickable = false
            etStatusMitra.isEnabled = false
            etStatusMitra.background =
                ContextCompat.getDrawable(etStatusMitra.context, R.drawable.custom_profile)

            tahungabung?.setText(tahunGabungValue)

            spinner_jenis_mitra?.isEnabled = false
            spinner_jenis_mitra?.background = ContextCompat.getDrawable(this,
                R.drawable.custom_profile
            )

            spinner_status_mitra?.isEnabled= false
            spinner_status_mitra?.background = ContextCompat.getDrawable(this,
                R.drawable.custom_profile
            )

        }

        observeViewModel()
        setSpinnerStatusMitra()
        setSpinnerjenisMitra()

        mDaftarMitraDetailViewModel2.responseSuccesStatus.observe(this){
            setSpinnerStatusMitra()
        }

        mDaftarMitraDetailViewModel2.responseSuccesJenis.observe(this){
            setSpinnerjenisMitra()
        }

    }

    private fun observeViewModel() {
        mDaftarMitraDetailViewModel2.mMessageResponse.observe(this){
            Toast.makeText(this@DetailKerjasamaMitraActivity, it, Toast.LENGTH_LONG).show()
        }

    }

    fun setSpinnerjenisMitra(){
        val arrayStringTahun = arrayListOf("Pilih Jenis Mitra")
        arrayStringTahun.addAll(mDaftarMitraDetailViewModel2.mDataJenisMitra.map {
            it.label
        })
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

        var positionJenisMitra = 0
        var position = 0
        mDaftarMitraDetailViewModel2.mDataJenisMitra.forEach {
            position += 1
            if(jenisMitra.equals(it.value)){
                positionJenisMitra = position
            }
        }

        if(!jenisMitra.isNullOrEmpty()){
            spinner_jenis_mitra?.setSelection(positionJenisMitra)
        }

    }

    fun setSpinnerStatusMitra() {
        val arrayStringWilayah = arrayListOf("Pilih Status")
        arrayStringWilayah.addAll(mDaftarMitraDetailViewModel2.mDataStatusMitra.map {
            it.label
        })
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

        var positionStatusMitra = 0
        var position = 0
        mDaftarMitraDetailViewModel2.mDataStatusMitra.forEach {
            position += 1
            if(statusMitra.equals(it.value)){
                positionStatusMitra = position
            }
        }

        if(!statusMitra.isNullOrEmpty()){
            spinner_status_mitra?.setSelection(positionStatusMitra)
        }


    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")
}