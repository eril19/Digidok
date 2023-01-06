package com.example.digidok

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
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
    var tahunGabungValue = ""
    var legalWp:Long = 0
    var tahun = 0
    var spinner_jenis_mitra: Spinner? = null
    val listJenisMitra : ArrayList<JenisMitraModel> = ArrayList()
    var isEdit: String = ""
    var idMitraCheck : String = ""
    var spinner_status_mitra: Spinner? = null
    val listStatusMitra : ArrayList<StatusMitraModel> = ArrayList()
    var data: MitraDetailModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail2)

        supportActionBar?.hide()
        isEdit = intent.getStringExtra("menu2") ?: ""
        data = intent.getParcelableExtra("dataDetail")
        idMitraCheck = intent.getStringExtra("id") ?: ""

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

        getJenisMitra()
        getStatusMitra()
        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
            onBackPressed()
        }

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, DaftarMitraActivity::class.java))
            finish()
        }

        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        backArrow.setOnClickListener {
            onBackPressed()
        }

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Detail Mitra")

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, NotificationActivity::class.java))
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

                UpdateData(
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
                startActivity(Intent(this@MitraDetailActivity2, DaftarMitraActivity::class.java))
            }

        }
        else{
            etSimpan.setOnClickListener {

                tahunGabungValue = etTahunGabung.text.toString()

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
                    tahunGabungValue,
                    jenisMitra,
                    statusMitra,
                    legalWp,
                    companyProfile
                )
                startActivity(Intent(this@MitraDetailActivity2, DaftarMitraActivity::class.java))
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
            spinner_jenis_mitra?.background = ContextCompat.getDrawable(this,R.drawable.custom_profile)

            spinner_status_mitra?.isEnabled= false
            spinner_status_mitra?.background = ContextCompat.getDrawable(this,R.drawable.custom_profile)

        }

//        setSpinnerKategori()
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
        tahunGabung: String,
        jenisMitra: String,
        statusMitra: String,
        legalWp:Long,
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
            legalWp = legalWp,
            object : DataSource.InsertMitraCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading = false
                    if (data.isSuccess) {

                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                    Toast.makeText(this@MitraDetailActivity2, message, Toast.LENGTH_LONG).show()


                }

                override fun onFinish() {
                    isLoading = false
                }

            })
    }

    fun UpdateData(
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
        tahunGabung: String,
        jenisMitra: String,
        statusMitra: String,
        legalWp:Long,
        companyProfile: String,
        id:String
    ) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.updateMitra(
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
            legalWp = legalWp,
            id = id,
            object : DataSource.updateMitraCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading = false
                    if (data.isSuccess) {

                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                    Toast.makeText(this@MitraDetailActivity2, message, Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
                    isLoading = false
                }

            })
    }

    fun setSpinnerjenisMitra(){
        val arrayStringTahun = arrayListOf("Pilih Jenis Mitra")
        arrayStringTahun.addAll(listJenisMitra.map {
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
        listJenisMitra.forEach {
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
        arrayStringWilayah.addAll(listStatusMitra.map {
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
        listStatusMitra.forEach {
            position += 1
            if(statusMitra.equals(it.value)){
                positionStatusMitra = position
            }
        }

        if(!statusMitra.isNullOrEmpty()){
            spinner_status_mitra?.setSelection(positionStatusMitra)
        }


    }

    fun getJenisMitra(){
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getJenisMitra(
            token = Preferences.isToken(context = this@MitraDetailActivity2),
            object : DataSource.jenisMitraCallback {
                override fun onSuccess(data: BaseApiModel<jenisMitramodel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listJenisMitra.clear()
                        data.data?.dataJenisMitra?.forEach {
                            listJenisMitra?.add(
                                JenisMitraModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
//                        setList()
                        setSpinnerjenisMitra()
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

    fun getStatusMitra(){
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getStatusMitra(
            token = Preferences.isToken(context = this@MitraDetailActivity2),
            object : DataSource.statusMitraCallback {
                override fun onSuccess(data: BaseApiModel<statusMitramodel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listJenisMitra.clear()
                        data.data?.dataStatusMitra?.forEach {
                            listStatusMitra?.add(
                                StatusMitraModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
//                        setList()
                        setSpinnerStatusMitra()
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

    fun getIndex(spinner: Spinner,  myString : String): Int {

        var index = 0
        var i = 0
        for ( i in 0..spinner.getCount()){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }

        return index
    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")
}