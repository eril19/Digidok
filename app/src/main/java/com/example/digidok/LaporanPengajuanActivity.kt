package com.example.digidok

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import com.example.digidok.databinding.ActivityMainBinding
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
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.data.model.laporanAsetDikerjasamakanModel
import com.example.digidok.data.model.laporanKerjasamaModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class LaporanPengajuanActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var laporanPengajuan: ArrayList<LaporanPengajuanModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"

    var tahun = 2017
    var wilayah = ""
    var status = "SEMUA"
    var kelurahan = ""

    var spinnerTahun : Spinner? = null
    val listTahun = arrayListOf("2022", "2021", "2020","2019", "2018", "2017","2016", "2015", "2014")

    var spinnerWilayah : Spinner? = null
    val listWilayah = arrayListOf("Wilayah 1", "Wilayah 2", "Wilayah 3")

    var spinnerKelurahan : Spinner? = null
    val listKelurahan = arrayListOf("Kelurahan 1", "Kelurahan 2", "Kelurahan 3")

    var spinnerStatus : Spinner? = null
    val listStatus = arrayListOf("SEMUA","DIKIRIM", "DRAFT", "DIKEMBALIKAN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_laporan_pengajuan)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listTahun)
        val header = findViewById<TextView>(R.id.header_title)

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
        spinnerWilayah = findViewById<Spinner>(R.id.spinner_wilayah)
        spinnerKelurahan = findViewById<Spinner>(R.id.spinner_kelurahan)
        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)

        spinnerTahun?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position-1 == 0){
                    tahun= 2022
                    getLaporanKerjasama("SEMUA",2022,"")
                }
                else if(position-1 == 1){
                    tahun= 2021
                    getLaporanKerjasama("SEMUA",2021,"")
                }
                else if(position-1 == 2){
                    tahun= 2020
                    getLaporanKerjasama("SEMUA",2020,"")
                }
                else if(position-1 == 3){
                    tahun= 2019
                    getLaporanKerjasama("SEMUA",2019,"")
                }
                else if(position-1 == 4){
                    tahun= 2018
                    getLaporanKerjasama("SEMUA",2018,"")
                }
                else if(position-1 == 5){
                    tahun= 2017
                    getLaporanKerjasama("SEMUA",2017,"")
                }
                else if(position-1 == 6){
                    tahun= 2016
                    getLaporanKerjasama("SEMUA",2016,"")
                }
                else if(position-1 == 7){
                    tahun= 2015
                    getLaporanKerjasama("SEMUA",2015,"")
                }
                else if(position-1 == 8){
                    tahun= 2014
                    getLaporanKerjasama("SEMUA",2014,"")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

//        spinnerKelurahan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }
//
//        spinnerWilayah?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }

        spinnerStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position-1 == 0){
                    status = "SEMUA"
                    getLaporanKerjasama("SEMUA",2022,"")
                }
                else if(position-1 == 1){
                    status = "DIKIRIM"
                    getLaporanKerjasama("DIKIRIM",2021,"")
                }
                else if(position-1 == 2){
                    status = "DRAFT"
                    getLaporanKerjasama("DRAFT",2020,"")
                }
                else if(position-1 == 3){
                    status = "DIKEMBALIKAN"
                    getLaporanKerjasama("DIKEMBALIKAN",2019,"")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        header.setText("Laporan Pengajuan Kerjasama")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@LaporanPengajuanActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }


        setSpinnerKategori()
        setList()
        getLaporanKerjasama(status, tahun, kelurahan)
    }

    fun setSpinnerKategori() {
        val arrayStringTahun = arrayListOf("Pilih Tahun")
        arrayStringTahun.addAll(listTahun)
        spinnerTahun?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringTahun) {
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

        val arrayStringWilayah = arrayListOf("Pilih Wilayah")
        arrayStringWilayah.addAll(listWilayah)
        spinnerWilayah?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringWilayah) {
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

        val arrayStringKelurahan = arrayListOf("Pilih Kelurahan")
        arrayStringKelurahan.addAll(listKelurahan)
        spinnerKelurahan?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringKelurahan) {
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

        val arrayStringStatus = arrayListOf("Pilih Status")
        arrayStringStatus.addAll(listStatus)
        spinnerStatus?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringStatus) {
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
    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_laporan_pengajuan)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = LaporanPengajuanAdapter(this, laporanPengajuan){

        }

    }

    fun getLaporanKerjasama(statusFitler:String,tahunFilter:Int,kelurahanFilter:String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getLaporanKerjasama(
            token = Preferences.isToken(context = this@LaporanPengajuanActivity),
            start = start,
            row = 10,
            order = order,
            sortColumn = sortColumn,
            search = "",
            statusFilter = statusFitler,
            tahunFilter = tahunFilter,
            kelurahanFilter = kelurahanFilter,
            object : DataSource.laporanKerjasamaCallback {
                override fun onSuccess(data: BaseApiModel<laporanKerjasamaModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        laporanPengajuan.clear()
                        data.data?.dataDokumen?.forEach {
                            laporanPengajuan?.add(
                                LaporanPengajuanModel(
                                    header_color = if (it?.status == 0) {
                                        "Tidak Aktif"
                                    } else if (it?.status == 1) {
                                        "Aktif"
                                    } else {
                                        ""
                                    },
                                    id_pks = it?.idPks.safe(),
                                    jenis_kerjasama = it?.kategoriPks.safe(),
                                    no_surat = it?.noPks.safe(),
                                    jenis_bmd = it?.objekPks.safe(),
                                    nilai_pks = "Rp. " + it?.nilaiPks.safe(),
                                    nama_mitra = it?.namaMitra.safe(),
                                    perihal = it?.perihalPks.safe(),
                                    id_mitra = it?.idMitra.safe(),
                                    alamatMitra = it?.alamatMitra.safe(),
                                    periodeAkhir = it?.periodeAkhir.safe(),
                                    periodeAwal = it?.periodeAwal.safe()


                                )
                            )
                        }
                        setList()
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

}
