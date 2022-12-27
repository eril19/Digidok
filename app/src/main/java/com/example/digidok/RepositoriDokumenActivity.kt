package com.example.digidok

import android.content.Intent
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
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class RepositoriDokumenActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var repositoriDokumen: ArrayList<RepositoriDokumenModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"

    var tahun = 2017
    var kota = ""
    var status = ""
    var kelurahan = ""

    var spinnerTahun : Spinner? = null
    val listTahun : ArrayList<TahunModel> = ArrayList()

    var spinnerKota : Spinner? = null
    val listKota : ArrayList<KotaModel> = ArrayList()

    var spinnerKelurahan : Spinner? = null
    val listKelurahan : ArrayList<KelurahanModel> = ArrayList()

    var spinnerStatus : Spinner? = null
    val listStatus = arrayListOf("SEMUA","DIKIRIM", "DRAFT", "DIKEMBALIKAN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_repositori_dokumen)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listTahun)

        getTahun()
        getKota()

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
        spinnerKota = findViewById<Spinner>(R.id.spinner_wilayah)
        spinnerKelurahan = findViewById<Spinner>(R.id.spinner_kelurahan)
        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        val header = findViewById<TextView>(R.id.header_title)

        spinnerTahun?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    tahun = listTahun.get(position-1).value.safe().toInt()
                    getRepositoriDokumen(status,tahun,kelurahan)
//                    minta  filter kota
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerKota?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    kota = listKota.get(position-1).value.safe()
                    getKelurahan(kota)
//                    getRepositoriDokumen(status,tahun,kota)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerKelurahan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    kelurahan = listKelurahan.get(position-1).value.safe()
                    getRepositoriDokumen(status,tahun,kelurahan)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        header.setText("Repositori Dokumen")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            onBackPressed()
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@RepositoriDokumenActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@RepositoriDokumenActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@RepositoriDokumenActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@RepositoriDokumenActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@RepositoriDokumenActivity, NotificationActivity::class.java))
        }

//        setSpinnerKategori()
        setList()
        getRepositoriDokumen(status,tahun,kelurahan)
    }

    fun setSpinnerKategori() {
        val arrayStringTahun = arrayListOf("Pilih Tahun")
        arrayStringTahun.addAll(listTahun.map {
            it.label
        })
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
        arrayStringWilayah.addAll(listKota.map {
            it.label
        })
        spinnerKota?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringWilayah) {
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
        arrayStringKelurahan.addAll(listKelurahan.map {
            it.label
        })
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
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_repositori)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = RepositoriDokumemAdapter(this,  repositoriDokumen, object:RepositoriDokumemAdapter.onItemClickListener{
            override fun onItemClick(position: Int,                nama: String,
                                     nilai: String,
                                     jenisKerjasama: String,
                                     pks: String) {

                val i = Intent(this@RepositoriDokumenActivity, CekDokumenActivity::class.java)
                i.putExtra("Cek Dokumen",repositoriDokumen[position])
                i.putExtra("nama",nama)
                i.putExtra("nilai",nilai)
                i.putExtra("pks",pks)
                i.putExtra("jenisKerjasama",jenisKerjasama)
                startActivity(i)
            }

        }
        ){

        }

    }

    fun getRepositoriDokumen(statusFitler:String,tahunFilter:Int,kelurahanFilter:String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getLaporanAsetDikerjasamakan(
            token = Preferences.isToken(context = this@RepositoriDokumenActivity),
            start = start,
            row = 10,
            order = order,
            sortColumn = sortColumn,
            search = "",
            statusFilter = statusFitler,
            tahunFilter = tahunFilter,
            kelurahanFilter = kelurahanFilter,
            object : DataSource.laporanAsetDikerjasamakanCallback {
                override fun onSuccess(data: BaseApiModel<laporanAsetDikerjasamakanModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        repositoriDokumen.clear()
                        data.data?.dataDokumen?.forEach {
                            repositoriDokumen?.add(
                                RepositoriDokumenModel(
                                    header_color = it?.statusLabel.safe(),
                                    nama_mitra = it?.namaMitra.safe(),
                                    jenis_kerjasama = it?.kategoriPks.safe(),
                                    no_surat = it?.idPks.safe(),
                                    harga = "Rp. " + it?.nilaiPks.toString().safe(),
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

    fun getTahun() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getTahun(
            token = Preferences.isToken(context = this@RepositoriDokumenActivity),
            object : DataSource.tahunCallback {
                override fun onSuccess(data: BaseApiModel<tahunModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listTahun.clear()
                        data.data?.dataTahun?.forEach {
                            listTahun?.add(
                                TahunModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        setList()
                        setSpinnerKategori()
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

    fun getKelurahan(idKota:String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getKelurahan(
            token = Preferences.isToken(context = this@RepositoriDokumenActivity),
            idKota = idKota,
            object : DataSource.kelurahanCallback {
                override fun onSuccess(data: BaseApiModel<kelurahanModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listKota.clear()
                        data.data?.dataKelurahan?.forEach {
                            listKelurahan?.add(
                                KelurahanModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        setList()
                        setSpinnerKategori()
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

    fun getKota() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getKota(
            token = Preferences.isToken(context = this@RepositoriDokumenActivity),
            object : DataSource.kotaCallback {
                override fun onSuccess(data: BaseApiModel<kotaModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        listKota.clear()
                        data.data?.dataKota?.forEach {
                            listKota?.add(
                                KotaModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        setList()
                        setSpinnerKategori()
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
