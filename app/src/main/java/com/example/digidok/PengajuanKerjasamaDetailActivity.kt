package com.example.digidok

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class PengajuanKerjasamaDetailActivity : AppCompatActivity() {

    var isStatusEdit: String = ""
    var isLoading: Boolean = false
    var pengajuanKerjasamaDetail: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"
    var statusFilter = "1"
    var idPkscheck = ""
    var no_pengajuan: EditText? = null
    var nama_mitra: EditText? = null
    var ETtujuan: EditText? = null
    var no_surat: EditText? = null
    var tgl_surat: EditText? = null
    var Objek: EditText? = null
    var nilai_: EditText? = null
    var tgl_akhir: EditText? = null
    var tgl_mulai: EditText? = null
    var prihal: EditText? = null
    var skema: EditText? = null
    var url = ""

    var idMitra = ""
    var idKategoriPks= ""
    var idTujuanPks= ""
    var nomorSurat= ""
    var tanggalSurat= ""
    var objek= ""
    var nilai = ""
    var tanggalMulai= ""
    var tanggalAkhir= ""
    var perihal= ""
    var dokumen= ""

//    var buttonDokumen : Button ? = null

    var spinnerMitra : Spinner? = null
    val listMitra :ArrayList<ListMitraModel> = ArrayList()


    var spinnerSkemaPemanfaatan : Spinner? = null
    val listSkemaPemanfaatan :ArrayList<KategoriPKSModel> = ArrayList()

    var spinnerTujuan : Spinner? = null
    val listTujuan :ArrayList<TujuanPKSModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail)



        supportActionBar?.hide()

        idPkscheck = intent.getStringExtra("idPks") ?: ""

        spinnerMitra = findViewById<Spinner>(R.id.spinner_mitra)
        spinnerMitra?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (listMitra.size!=0){
                if(position!=0){
                    idMitra = listMitra[position-1].value.safe()
                }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinnerSkemaPemanfaatan = findViewById<Spinner>(R.id.spinner_skema_pemanfaatan)
        spinnerSkemaPemanfaatan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               if (listSkemaPemanfaatan.size!=0){
                if(position!=0){
                    idKategoriPks = listSkemaPemanfaatan[position-1].value.safe()
                }
               }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinnerTujuan = findViewById<Spinner>(R.id.spinner_tujuan)
        spinnerTujuan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (listTujuan.size != 0){
                if(position!=0){
                    idTujuanPks = listTujuan[position-1].value.safe()
                }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val simpan = findViewById<Button>(R.id.simpan_pengajuan_btn)
        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        val prev = findViewById<Button>(R.id.prev_detail_btn)
        no_pengajuan = findViewById<EditText>(R.id.no_pengajuan)
        nama_mitra = findViewById<EditText>(R.id.nama_mitra)
       // ETtujuan = findViewById<EditText>(R.id.tujuan)
        no_surat = findViewById<EditText>(R.id.no_surat)
        tgl_surat = findViewById<EditText>(R.id.tgl_surat)
        Objek = findViewById<EditText>(R.id.objek)
        tgl_mulai = findViewById<EditText>(R.id.tgl_mulai)
        nilai_ = findViewById<EditText>(R.id.nilai)
        tgl_akhir = findViewById<EditText>(R.id.tgl_akhir)
        prihal = findViewById<EditText>(R.id.perihal)
      //  skema = findViewById<EditText>(R.id.skema_pemanfaatan)
        val buttonDokumen = findViewById<ImageButton>(R.id.buttonDokumen)

        if (!idPkscheck.equals("")){
            getPengajuanDetail(idPkscheck)
        }

        getListMitra()
        getKategoriPKS()
        getTujuanPKS()

        isStatusEdit = intent.getStringExtra("status") ?: ""

        if (isStatusEdit.equals("Edit", true) || isStatusEdit.equals("Tambah",true)) {
            no_pengajuan?.isEnabled = false
            no_pengajuan?.setText("(Auto)")
//            no_pengajuan?.background =
//                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            nama_mitra?.isEnabled = true
            nama_mitra?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            ETtujuan?.isEnabled = true
            ETtujuan?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            no_surat?.isEnabled = true
            no_surat?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            tgl_akhir?.isEnabled = true
            tgl_akhir?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            tgl_mulai?.isEnabled = true
            tgl_mulai?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            tgl_surat?.isEnabled = true
            tgl_surat?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            prihal?.isEnabled = true
            prihal?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            nilai_?.isEnabled = true
            nilai_?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            Objek?.isEnabled = true
            Objek?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            skema?.isEnabled = true
            skema?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            next_detail_btn.visibility = View.GONE
            prev.visibility = View.GONE

            close_detail_btn.visibility = View.GONE

            simpan.setOnClickListener {

//                idMitra = nama_mitra?.text.toString()
//                idKategoriPks = skema?.text.toString()
//                idTujuanPks = ETtujuan?.text.toString()
                nomorSurat = no_surat?.text.toString()
                tanggalSurat = tgl_surat?.text.toString()
                objek = Objek?.text.toString()
                nilai = nilai_?.text.toString()
                tanggalMulai = tgl_mulai?.text.toString()
                tanggalAkhir = tgl_akhir?.text.toString()
                perihal = prihal?.text.toString()
                dokumen = ""


                if (isStatusEdit.equals("Edit", true)){
//                    update
                }
                else if(isStatusEdit.equals("Tambah",true)){
                    insertPengajuan(idMitra, idKategoriPks, idTujuanPks, nomorSurat, tanggalSurat, objek, nilai, tanggalMulai, tanggalAkhir, perihal, dokumen)
                }
            }

        }
        else {
            no_pengajuan?.isEnabled = false
            no_pengajuan?.setText("(Auto)")

            simpan.visibility = View.GONE

            spinnerTujuan?.isEnabled = false
            spinnerTujuan?.background = ContextCompat.getDrawable(this,R.drawable.custom_profile)
            spinnerMitra?.isEnabled = false
            spinnerMitra?.background = ContextCompat.getDrawable(this,R.drawable.custom_profile)
            spinnerSkemaPemanfaatan?.isEnabled = false
            spinnerSkemaPemanfaatan?.background = ContextCompat.getDrawable(this,R.drawable.custom_profile)
            skema?.isEnabled = false
            nama_mitra?.isEnabled = false
            ETtujuan?.isEnabled = false
            no_surat?.isEnabled = false
            tgl_akhir?.isEnabled = false
            tgl_mulai?.isEnabled = false
            tgl_surat?.isEnabled = false
            prihal?.isEnabled = false
            nilai_?.isEnabled = false
            Objek?.isEnabled = false
        }



        close_detail_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@PengajuanKerjasamaDetailActivity,
                    PengajuanKerjasamaActivity::class.java
                )
            )
            finish()
        }

        backArrow.setOnClickListener {
            startActivity(
                Intent(
                    this@PengajuanKerjasamaDetailActivity,
                    PengajuanKerjasamaActivity::class.java
                )
            )
            finish()
        }

        val header = findViewById<TextView>(R.id.header_title)

        if(isStatusEdit.equals("Tambah",true)){
            header.setText("Tambah Pengajuan Kerjasama")
        }
        else{
        header.setText("Detail Pengajuan Kerjasama")
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity, NotificationActivity::class.java))
        }

        next_detail_btn.setOnClickListener {
            val i = Intent(
                this@PengajuanKerjasamaDetailActivity,
                DataAsetdikerjasamakanActivity::class.java
            )
            i.putExtra("hideTelaah", true)
            i.putExtra("idPks",idPkscheck)
            startActivity(i)
        }

        buttonDokumen.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        setList()

    }

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DataAsetdiKerjasamakanAdapter(
            this,
            pengajuanKerjasamaDetail,
            object : DataAsetdiKerjasamakanAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    TODO("Not yet implemented")
                }

            }) {

        }

    }

    fun getListMitra() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getListMitra(
            token = Preferences.isToken(context = this@PengajuanKerjasamaDetailActivity),
            object : DataSource.listMitraCallback {
                override fun onSuccess(data: BaseApiModel<listMitra?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listMitra.clear()
                        data.data?.dataMitra?.forEach {
                            listMitra?.add(
                                ListMitraModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
//                        setList()
                        setSpinnerMitra()
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

    fun getKategoriPKS() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getKategoriPKS(
            token = Preferences.isToken(context = this@PengajuanKerjasamaDetailActivity),
            object : DataSource.kategoriPKSCallback {
                override fun onSuccess(data: BaseApiModel<kategoriPKSmodel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listSkemaPemanfaatan.clear()
                        data.data?.dataKategoriPks?.forEach {
                            listSkemaPemanfaatan?.add(
                                KategoriPKSModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
//                        setList()
                        setSpinnerKategoriPKS()
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

    fun getTujuanPKS() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getTujuanPKS(
            token = Preferences.isToken(context = this@PengajuanKerjasamaDetailActivity),
            object : DataSource.tujuanPKSCallback {
                override fun onSuccess(data: BaseApiModel<tujuanPKSmodel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listTujuan.clear()
                        data.data?.dataTujuanPks?.forEach {
                            listTujuan?.add(
                                TujuanPKSModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
//                        setList()
                        setSpinnerTujuanPKS()
                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                    Toast.makeText(this@PengajuanKerjasamaDetailActivity, message, Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
                    isLoading = false
                }

            })
    }

    fun getPengajuanDetail(idPks: String) {
        com.example.digidok.isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDaftarPengajuanKerjasamaDetail(
            token = Preferences.isToken(context = this@PengajuanKerjasamaDetailActivity),
            id = idPks,
            object : DataSource.daftarPengajuanDetailCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>) {
                    com.example.digidok.isLoading = false
                    if (data.isSuccess) {
                        no_pengajuan?.setText(data.data?.noPengajuan)
                        nama_mitra?.setText(data.data?.mitra)
                        skema?.setText(data.data?.skemaPemanfaatan)
                        ETtujuan?.setText(data.data?.tujuan)
                        no_surat?.setText(data.data?.nomorSurat)
                        tgl_surat?.setText(data.data?.tanggalSurat)
                        Objek?.setText(data.data?.objek)
                        nilai_?.setText("Rp." + data.data?.nilai.toString())
                        tgl_mulai?.setText(data.data?.tanggalMulai)
                        tgl_akhir?.setText(data.data?.tanggalAkhir)
                        prihal?.setText(data.data?.perihal)
                        url = data.data?.dokumen.toString()
                        idMitra = data.data?.idMitra.toString()
                        idTujuanPks = data.data?.idTujuan.toString()
                        idKategoriPks = data.data?.idSkemaPemanfaatan.toString()
                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                    Toast.makeText(this@PengajuanKerjasamaDetailActivity, message, Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
                    isLoading = false
//                    Toast.makeText(this@PengajuanKerjasamaDetailActivity, "Data selesai dimuat", Toast.LENGTH_LONG).show()
                }

            })
    }

    fun insertPengajuan(
        idMitra: String,
        idKategoriPks: String,
        idTujuanPks: String,
        nomorSurat: String,
        tanggalSurat: String,
        objek: String,
        nilai: String,
        tanggalMulai: String,
        tanggalAkhir: String,
        perihal: String,
        dokumen: String,
    ){
        com.example.digidok.isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.InsertPengajuan(
            token = Preferences.isToken(context = this@PengajuanKerjasamaDetailActivity),
            idMitra = idMitra,
            idKategoriPks = idKategoriPks,
            idTujuanPks = idTujuanPks,
            nomorSurat = nomorSurat,
            tanggalSurat = tanggalSurat,
            objek = objek,
            nilai = nilai,
            tanggalMulai = tanggalMulai,
            tanggalAkhir = tanggalAkhir,
            perihal = perihal,
            dokumen = dokumen,
            object : DataSource.InsertPengajuanCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    com.example.digidok.isLoading = false
                    if (data.isSuccess) {

                    }
                }

                override fun onError(message: String) {
                    Toast.makeText(this@PengajuanKerjasamaDetailActivity, "Ada yang salah", Toast.LENGTH_LONG).show()
                    com.example.digidok.isLoading = false
                }

                override fun onFinish() {
                    com.example.digidok.isLoading = false
                }

            })
    }

    fun setSpinnerMitra(){
        val arrayStringMitra = arrayListOf("Pilih Mitra")
        arrayStringMitra.addAll(listMitra.map {
            it.label
        })
        spinnerMitra?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringMitra) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
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
        listMitra.forEach {
            position += 1
            if(idMitra.equals(it.value)){
                positionJenisMitra = position
            }
        }

        if(!idMitra.isNullOrEmpty()){
            spinnerMitra?.setSelection(positionJenisMitra)
        }
    }

    fun setSpinnerTujuanPKS(){

        val arrayStringTujuan = arrayListOf("Pilih Tujuan")
        arrayStringTujuan.addAll(listTujuan.map {
            it.label
        })
        spinnerTujuan?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringTujuan) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
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
        listTujuan.forEach {
            position += 1
            if(idTujuanPks.equals(it.value)){
                positionJenisMitra = position
            }
        }

        if(!idTujuanPks.isNullOrEmpty()){
            spinnerTujuan?.setSelection(positionJenisMitra)
        }
    }

    fun setSpinnerKategoriPKS() {
        val arrayStringSkema = arrayListOf("Pilih Skema Pemanfaatan")
        arrayStringSkema.addAll(listSkemaPemanfaatan.map {
            it.label
        })
        spinnerSkemaPemanfaatan?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringSkema) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
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
        listSkemaPemanfaatan.forEach {
            position += 1
            if(idKategoriPks.equals(it.value)){
                positionJenisMitra = position
            }
        }

        if(!idKategoriPks.isNullOrEmpty()){
            spinnerSkemaPemanfaatan?.setSelection(positionJenisMitra)
        }
    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")


}