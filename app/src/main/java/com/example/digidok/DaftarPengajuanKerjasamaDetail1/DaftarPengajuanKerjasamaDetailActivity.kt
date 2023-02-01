package com.example.digidok.DaftarPengajuanKerjasamaDetail1

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarPengajuanKerjasama.DaftarPengajuanKerjasamaActivity
import com.example.digidok.DaftarPengajuanKerjasamaDetail2.DataAsetdiKerjasamakanAdapter
import com.example.digidok.DaftarPengajuanKerjasamaDetail2.DataAsetdikerjasamakanActivity
import com.example.digidok.DaftarPengajuanKerjasamaDetail3.DaftarPengajuanKerjasamaDetail3ViewModel
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.SpinnerModel.KategoriPKSModel
import com.example.digidok.SpinnerModel.ListMitraModel
import com.example.digidok.SpinnerModel.TujuanPKSModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DaftarPengajuanKerjasamaDetailActivity : AppCompatActivity() {

    var isStatusEdit: String = ""
    var isLoading: Boolean = false
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
    var idKategoriPks = ""
    var idTujuanPks = ""
    var nomorSurat = ""
    var tanggalSurat = ""
    var objek = ""
    var nilai = ""
    var tanggalMulai = ""
    var tanggalAkhir = ""
    var perihal = ""
    var dokumen = ""


    var spinnerMitra: Spinner? = null
    var spinnerSkemaPemanfaatan: Spinner? = null
    var spinnerTujuan: Spinner? = null
    var dokumenPKS: TextView? = null
    var pdfPic: ImageView? = null
    var tnc: TextView? = null

    lateinit var mDaftarPengajuanKerjasamaDetailViewModel: DaftarPengajuanKerjasamaDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail)

        supportActionBar?.hide()

        idPkscheck = intent.getStringExtra("idPks") ?: ""

        mDaftarPengajuanKerjasamaDetailViewModel =
            ViewModelProvider(this@DaftarPengajuanKerjasamaDetailActivity).get(
                DaftarPengajuanKerjasamaDetailViewModel::class.java
            )
        mDaftarPengajuanKerjasamaDetailViewModel.token.value =
            Preferences.isToken(this@DaftarPengajuanKerjasamaDetailActivity)

        spinnerMitra = findViewById<Spinner>(R.id.spinner_mitra)
        spinnerMitra?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (mDaftarPengajuanKerjasamaDetailViewModel.mDataMitra.size != 0) {
                    if (position != 0) {
                        idMitra = mDaftarPengajuanKerjasamaDetailViewModel.mDataMitra[position - 1].value.safe()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinnerSkemaPemanfaatan = findViewById<Spinner>(R.id.spinner_skema_pemanfaatan)
        spinnerSkemaPemanfaatan?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (mDaftarPengajuanKerjasamaDetailViewModel.mDataKategori.size != 0) {
                        if (position != 0) {
                            idKategoriPks = mDaftarPengajuanKerjasamaDetailViewModel.mDataKategori[position - 1].value.safe()
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        spinnerTujuan = findViewById<Spinner>(R.id.spinner_tujuan)
        spinnerTujuan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (mDaftarPengajuanKerjasamaDetailViewModel.mDataTujuan.size != 0) {
                    if (position != 0) {
                        idTujuanPks = mDaftarPengajuanKerjasamaDetailViewModel.mDataTujuan[position - 1].value.safe()
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

        if (!idPkscheck.equals("")) {
            mDaftarPengajuanKerjasamaDetailViewModel.getPengajuanKerjasamaDetail(idPkscheck)
        }
        else{
            mDaftarPengajuanKerjasamaDetailViewModel.getListMitra()
            mDaftarPengajuanKerjasamaDetailViewModel.getKategoriPKS()
            mDaftarPengajuanKerjasamaDetailViewModel.getTujuanPKS()

        }

//        mDaftarPengajuanKerjasamaDetailViewModel.getPengajuanKerjasamaDetail(idPkscheck?:"")
//        mDaftarPengajuanKerjasamaDetailViewModel.getListMitra()
//        mDaftarPengajuanKerjasamaDetailViewModel.getKategoriPKS()
//        mDaftarPengajuanKerjasamaDetailViewModel.getTujuanPKS()

        isStatusEdit = intent.getStringExtra("status") ?: ""

        if (isStatusEdit.equals("Edit", true) || isStatusEdit.equals("Tambah", true)) {
            no_pengajuan?.isEnabled = false
            no_pengajuan?.setText("(Auto)")
//            no_pengajuan?.background =
//                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            val dot = findViewById<LinearLayout>(R.id.dot)

            dot.visibility = View.GONE

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

                val back = findViewById<ImageView>(R.id.backbtn)

                back.setOnClickListener {
                    val intent = Intent(
                        this@DaftarPengajuanKerjasamaDetailActivity,
                        DaftarPengajuanKerjasamaActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }

                if (isStatusEdit.equals("Edit", true)) {
                    mDaftarPengajuanKerjasamaDetailViewModel.updatePengajuan(
                        idMitra,
                        idKategoriPks,
                        idTujuanPks,
                        nomorSurat,
                        tanggalSurat,
                        objek,
                        nilai,
                        tanggalMulai,
                        tanggalAkhir,
                        perihal,
                        idPkscheck,
                        dokumen
                    )
                    startActivity(
                        Intent(
                            this@DaftarPengajuanKerjasamaDetailActivity,
                            DaftarPengajuanKerjasamaActivity::class.java
                        )
                    )
                } else if (isStatusEdit.equals("Tambah", true)) {
                    mDaftarPengajuanKerjasamaDetailViewModel.insertPengajuan(
                        idMitra,
                        idKategoriPks,
                        idTujuanPks,
                        nomorSurat,
                        tanggalSurat,
                        objek,
                        nilai,
                        tanggalMulai,
                        tanggalAkhir,
                        perihal,
                        dokumen
                    )
                    startActivity(
                        Intent(
                            this@DaftarPengajuanKerjasamaDetailActivity,
                            DaftarPengajuanKerjasamaActivity::class.java
                        )
                    )
                }
            }

        } else {
            no_pengajuan?.isEnabled = false
            no_pengajuan?.setText("(Auto)")

            simpan.visibility = View.GONE

            spinnerTujuan?.isEnabled = false
            spinnerTujuan?.background = ContextCompat.getDrawable(this, R.drawable.custom_profile)
            spinnerMitra?.isEnabled = false
            spinnerMitra?.background = ContextCompat.getDrawable(this, R.drawable.custom_profile)
            spinnerSkemaPemanfaatan?.isEnabled = false
            spinnerSkemaPemanfaatan?.background = ContextCompat.getDrawable(
                this,
                R.drawable.custom_profile
            )
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
                    this@DaftarPengajuanKerjasamaDetailActivity,
                    DaftarPengajuanKerjasamaActivity::class.java
                )
            )
            finish()
        }

        backArrow.setOnClickListener {
            startActivity(
                Intent(
                    this@DaftarPengajuanKerjasamaDetailActivity,
                    DaftarPengajuanKerjasamaActivity::class.java
                )
            )
            finish()
        }

        val header = findViewById<TextView>(R.id.header_title)

        if (isStatusEdit.equals("Tambah", true)) {
            header.setText("Tambah Pengajuan Kerjasama")

            dokumenPKS = findViewById(R.id.title_Dokumen)
            pdfPic = findViewById(R.id.buttonDokumen)
            tnc = findViewById(R.id.pdfcapt)

            dokumenPKS?.visibility = View.GONE
            pdfPic?.visibility = View.GONE
            tnc?.visibility = View.GONE

        } else if (isStatusEdit.equals("Edit", true)) {
            header.setText("Edit Pengajuan Kerjasama")

            dokumenPKS = findViewById(R.id.title_Dokumen)
            pdfPic = findViewById(R.id.buttonDokumen)
            tnc = findViewById(R.id.pdfcapt)

            dokumenPKS?.visibility = View.GONE
            pdfPic?.visibility = View.GONE
            tnc?.visibility = View.GONE
        } else {
            header.setText("Detail Pengajuan Kerjasama")
        }

        val homeBtn: ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@DaftarPengajuanKerjasamaDetailActivity,
                    DashboardActivity::class.java
                )
            )
        }

        val homeBtn2: ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(
                Intent(
                    this@DaftarPengajuanKerjasamaDetailActivity,
                    DashboardActivity::class.java
                )
            )
        }

        val homeBtn3: ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(
                Intent(
                    this@DaftarPengajuanKerjasamaDetailActivity,
                    DashboardActivity::class.java
                )
            )
        }

        val profileBtn: ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@DaftarPengajuanKerjasamaDetailActivity,
                    ProfileActivity::class.java
                )
            )
        }

        val notificationBtn: ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@DaftarPengajuanKerjasamaDetailActivity,
                    NotificationActivity::class.java
                )
            )
        }

        next_detail_btn.setOnClickListener {
            val i = Intent(
                this@DaftarPengajuanKerjasamaDetailActivity,
                DataAsetdikerjasamakanActivity::class.java
            )
            i.putExtra("hideTelaah", true)
            i.putExtra("idPks", idPkscheck)
            startActivity(i)
        }

        buttonDokumen.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        observeViewModel()

//        setList()
        mDaftarPengajuanKerjasamaDetailViewModel.responseSuccesMitra.observe(this) {
            setSpinnerMitra()
        }
        mDaftarPengajuanKerjasamaDetailViewModel.responseSuccesStatus.observe(this) {
            setSpinnerKategoriPKS()
        }
        mDaftarPengajuanKerjasamaDetailViewModel.responseSuccesTujuan.observe(this) {
            setSpinnerTujuanPKS()
        }

    }

    private fun observeViewModel() {
        mDaftarPengajuanKerjasamaDetailViewModel.mMessageResponse.observe(this) {
            Toast.makeText(this@DaftarPengajuanKerjasamaDetailActivity, it, Toast.LENGTH_LONG)
                .show()
        }
        mDaftarPengajuanKerjasamaDetailViewModel.mitra.observe(this) {
            idMitra = mDaftarPengajuanKerjasamaDetailViewModel.mitra.value.safe()
        }
        mDaftarPengajuanKerjasamaDetailViewModel.skema.observe(this) {
            idKategoriPks = mDaftarPengajuanKerjasamaDetailViewModel.skema.value.safe()
        }
        mDaftarPengajuanKerjasamaDetailViewModel.tujuan.observe(this) {
            idTujuanPks = mDaftarPengajuanKerjasamaDetailViewModel.tujuan.value.safe()
        }
        mDaftarPengajuanKerjasamaDetailViewModel.nomorSurat.observe(this) {
            no_surat?.setText(mDaftarPengajuanKerjasamaDetailViewModel.nomorSurat.value)
        }
        mDaftarPengajuanKerjasamaDetailViewModel.tanggalSurat.observe(this) {
            tgl_surat?.setText(mDaftarPengajuanKerjasamaDetailViewModel.tanggalSurat.value)
        }
        mDaftarPengajuanKerjasamaDetailViewModel.tanggalMulai.observe(this) {
            tgl_mulai?.setText(mDaftarPengajuanKerjasamaDetailViewModel.tanggalMulai.value)
        }
        mDaftarPengajuanKerjasamaDetailViewModel.tanggalAkhir.observe(this) {
            tgl_akhir?.setText(mDaftarPengajuanKerjasamaDetailViewModel.tanggalAkhir.value)
        }
        mDaftarPengajuanKerjasamaDetailViewModel.perihal.observe(this) {
            prihal?.setText(mDaftarPengajuanKerjasamaDetailViewModel.perihal.value)
        }
        mDaftarPengajuanKerjasamaDetailViewModel.objek.observe(this) {
            Objek?.setText(mDaftarPengajuanKerjasamaDetailViewModel.objek.value)
        }
        mDaftarPengajuanKerjasamaDetailViewModel.nilai.observe(this) {
            nilai_?.setText(mDaftarPengajuanKerjasamaDetailViewModel.nilai.value)
        }
        mDaftarPengajuanKerjasamaDetailViewModel.perihal.observe(this) {
            prihal?.setText(mDaftarPengajuanKerjasamaDetailViewModel.perihal.value)
        }

    }

    fun setSpinnerMitra() {
        val arrayStringMitra = arrayListOf("Pilih Mitra")
        arrayStringMitra.addAll(mDaftarPengajuanKerjasamaDetailViewModel.mDataMitra.map {
            it.label
        })
        spinnerMitra?.adapter = object : ArrayAdapter<String>(
            this,
            R.layout.dd_text_status, arrayStringMitra
        ) {
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
        mDaftarPengajuanKerjasamaDetailViewModel.mDataMitra.forEach {
            position += 1
            if (idMitra.equals(it.value)) {
                positionJenisMitra = position
            }
        }

        if (!idMitra.isNullOrEmpty()) {
            spinnerMitra?.setSelection(positionJenisMitra)
        }
    }

    fun setSpinnerTujuanPKS() {

        val arrayStringTujuan = arrayListOf("Pilih Tujuan")
        arrayStringTujuan.addAll(mDaftarPengajuanKerjasamaDetailViewModel.mDataTujuan.map {
            it.label
        })
        spinnerTujuan?.adapter = object : ArrayAdapter<String>(
            this,
            R.layout.dd_text_status, arrayStringTujuan
        ) {
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
        mDaftarPengajuanKerjasamaDetailViewModel.mDataTujuan.forEach {
            position += 1
            if (idTujuanPks.equals(it.value)) {
                positionJenisMitra = position
            }
        }

        if (!idTujuanPks.isNullOrEmpty()) {
            spinnerTujuan?.setSelection(positionJenisMitra)
        }
    }

    fun setSpinnerKategoriPKS() {
        val arrayStringSkema = arrayListOf("Pilih Skema Pemanfaatan")
        arrayStringSkema.addAll(mDaftarPengajuanKerjasamaDetailViewModel.mDataKategori.map {
            it.label
        })
        spinnerSkemaPemanfaatan?.adapter = object : ArrayAdapter<String>(
            this,
            R.layout.dd_text_status, arrayStringSkema
        ) {
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
        mDaftarPengajuanKerjasamaDetailViewModel.mDataKategori.forEach {
            position += 1
            if (idKategoriPks.equals(it.value)) {
                positionJenisMitra = position
            }
        }

        if (!idKategoriPks.isNullOrEmpty()) {
            spinnerSkemaPemanfaatan?.setSelection(positionJenisMitra)
        }
    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")


}