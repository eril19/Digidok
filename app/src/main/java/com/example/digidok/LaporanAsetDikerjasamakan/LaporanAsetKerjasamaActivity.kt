package com.example.digidok.LaporanAsetDikerjasamakan

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.RepositoriDokumen.RepositoriDokumenViewModel
import com.example.digidok.SpinnerModel.KelurahanModel
import com.example.digidok.SpinnerModel.KotaModel
import com.example.digidok.SpinnerModel.TahunModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class LaporanAsetKerjasamaActivity : AppCompatActivity() {
    lateinit var mLaporanAsetKerjasamaViewModel: LaporanAsetKerjasamaViewModel
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
    val listKota : ArrayList<KotaModel> =  ArrayList()

    var spinnerKelurahan : Spinner? = null
    val listKelurahan : ArrayList<KelurahanModel> = ArrayList()

    var spinnerStatus : Spinner? = null
    val listStatus = arrayListOf("SEMUA","DIKIRIM", "DRAFT", "DIKEMBALIKAN", "DISETUJUI")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_laporan_aset)

        val adapter = ArrayAdapter(applicationContext, R.layout.dd_text_status, listTahun)
        val header = findViewById<TextView>(R.id.header_title)

        mLaporanAsetKerjasamaViewModel = ViewModelProvider(this@LaporanAsetKerjasamaActivity).get(LaporanAsetKerjasamaViewModel::class.java)
        mLaporanAsetKerjasamaViewModel.token.value = Preferences.isToken(this@LaporanAsetKerjasamaActivity)
        mLaporanAsetKerjasamaViewModel.row.value = "10"
        mLaporanAsetKerjasamaViewModel.order.value = "asc"
        mLaporanAsetKerjasamaViewModel.start.value = "0"
        mLaporanAsetKerjasamaViewModel.sortColumn.value = "no"

        mLaporanAsetKerjasamaViewModel.getTahun()
        mLaporanAsetKerjasamaViewModel.getKota()

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
        spinnerKota = findViewById<Spinner>(R.id.spinner_wilayah)
        spinnerKelurahan = findViewById<Spinner>(R.id.spinner_kelurahan)
        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)


        spinnerTahun?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    tahun = mLaporanAsetKerjasamaViewModel.mDataTahun.get(position-1).value.safe().toInt()
                    mLaporanAsetKerjasamaViewModel.getLaporanAset(status,tahun,kelurahan)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerKota?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    kota = mLaporanAsetKerjasamaViewModel.mDataKota.get(position-1).value.safe()
                    mLaporanAsetKerjasamaViewModel.getKelurahan(kota)
                    mLaporanAsetKerjasamaViewModel.getLaporanAset(status,tahun,kelurahan)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerKelurahan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    kelurahan = mLaporanAsetKerjasamaViewModel.mDataKelurahan.get(position-1).value.safe()
                    mLaporanAsetKerjasamaViewModel.getLaporanAset(status,tahun,kelurahan)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position-1 == 0){
                    status = "SEMUA"
                    mLaporanAsetKerjasamaViewModel.getLaporanAset("SEMUA",tahun,kelurahan)
                }
                else if(position-1 == 1){
                    status = "DIKIRIM"
                    mLaporanAsetKerjasamaViewModel.getLaporanAset("DIKIRIM",tahun,kelurahan)
                }
                else if(position-1 == 2){
                    status = "DRAFT"
                    mLaporanAsetKerjasamaViewModel.getLaporanAset("DRAFT",tahun,kelurahan)
                }
                else if(position-1 == 3){
                    status = "DIKEMBALIKAN"
                    mLaporanAsetKerjasamaViewModel.getLaporanAset("DIKEMBALIKAN",tahun,kelurahan)
                }
                else if(position-1 == 4){
                    status = "DISETUJUI"
                    mLaporanAsetKerjasamaViewModel.getLaporanAset("DISETUJUI",tahun,kelurahan)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        setSpinnerStatus()
        setSpinnerTahun()
        setSpinnerWilayah()
        setSpinnerKelurahan()

        val back = findViewById<ImageView>(R.id.backbtn)
        header.setText("Laporan Aset Dikerjasamakan")

        back.setOnClickListener {
            val intent = Intent(this@LaporanAsetKerjasamaActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@LaporanAsetKerjasamaActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@LaporanAsetKerjasamaActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@LaporanAsetKerjasamaActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@LaporanAsetKerjasamaActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@LaporanAsetKerjasamaActivity, NotificationActivity::class.java))
        }

        setList()
        mLaporanAsetKerjasamaViewModel.getLaporanAset(status,tahun,kelurahan)
    }

    fun setSpinnerTahun() {
        val arrayStringTahun = arrayListOf("Pilih Tahun")
        arrayStringTahun.addAll(listTahun.map {
            it.label
        })
        spinnerTahun?.adapter = object : ArrayAdapter<String>(this,
            R.layout.dd_text_status, arrayStringTahun) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context,
                            R.color.black
                        ))
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

    fun setSpinnerWilayah() {
        val arrayStringWilayah = arrayListOf("Pilih Wilayah")
        arrayStringWilayah.addAll(listKota.map {
            it.label
        })

        spinnerKota?.adapter = object : ArrayAdapter<String>(this,
            R.layout.dd_text_status, arrayStringWilayah) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context,
                            R.color.black
                        ))
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

    fun setSpinnerKelurahan() {
        val arrayStringKelurahan = arrayListOf("Pilih Kelurahan")
        arrayStringKelurahan.addAll(listKelurahan.map {
            it.label
        })

        spinnerKelurahan?.adapter = object : ArrayAdapter<String>(this,
            R.layout.dd_text_status, arrayStringKelurahan) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context,
                            R.color.black
                        ))
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

    fun setSpinnerStatus() {
        val arrayStringStatus = arrayListOf("Pilih Status")
        arrayStringStatus.addAll(listStatus)
        spinnerStatus?.adapter = object : ArrayAdapter<String>(this,
            R.layout.dd_text_status, arrayStringStatus) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context,
                            R.color.black
                        ))
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
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_laporan_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)
        recyclerview?.adapter = LaporanAsetKerjasamaAdapter(this, mLaporanAsetKerjasamaViewModel,object : LaporanAsetKerjasamaAdapter.onItemClickListener {

            override fun onItemClick(
                position: Int,
                nama: String,
                nilai: String,
                jenisKerjasama: String,
                pks: String
            ) {
                val i = Intent(this@LaporanAsetKerjasamaActivity, LaporanAsetDetailActivity::class.java)
                i.putExtra("laporanAset", mLaporanAsetKerjasamaViewModel.mData[position])
                startActivity(i)
            }
        }){


        }

    }

}
