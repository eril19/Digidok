package com.example.digidok.RepositoriDokumen

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
import com.example.digidok.*
import com.example.digidok.CekDokumen.CekDokumenActivity
import com.example.digidok.DaftarKJPP.DaftarKjppViewModel
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Dashboard.DashboardViewModel
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.SpinnerModel.KelurahanModel
import com.example.digidok.SpinnerModel.KotaModel
import com.example.digidok.SpinnerModel.TahunModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class RepositoriDokumenActivity : AppCompatActivity() {
    private var recyclerview: RecyclerView? = null
    lateinit var mRepositoriDokumenViewModel: RepositoriDokumenViewModel

    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"

    var tahun = 2017
    var kota = ""
    var status = ""
    var kelurahan = ""

    var spinnerTahun : Spinner? = null

    var spinnerKota : Spinner? = null

    var spinnerKelurahan : Spinner? = null

    var spinnerStatus : Spinner? = null
    val listStatus = arrayListOf("SEMUA","DIKIRIM", "DRAFT", "DIKEMBALIKAN","DISETUJUI")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_repositori_dokumen)

        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_repositori)

        mRepositoriDokumenViewModel = ViewModelProvider(this@RepositoriDokumenActivity).get(RepositoriDokumenViewModel::class.java)
        mRepositoriDokumenViewModel.token.value = Preferences.isToken(this@RepositoriDokumenActivity)
        mRepositoriDokumenViewModel.row.value = "10"
        mRepositoriDokumenViewModel.order.value = "asc"
        mRepositoriDokumenViewModel.start.value = "0"
        mRepositoriDokumenViewModel.sortColumn.value = "no"

        mRepositoriDokumenViewModel.getTahun()
        mRepositoriDokumenViewModel.getKota()

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
        spinnerKota = findViewById<Spinner>(R.id.spinner_wilayah)
        spinnerKelurahan = findViewById<Spinner>(R.id.spinner_kelurahan)
        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        val header = findViewById<TextView>(R.id.header_title)

        spinnerTahun?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    tahun = mRepositoriDokumenViewModel.mDataTahun.get(position-1).value.safe().toInt()
                    mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerKota?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    kota = mRepositoriDokumenViewModel.mDataKota.get(position-1).value.safe()
                    mRepositoriDokumenViewModel.getKelurahan(kota)
                    mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinnerKelurahan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=0){
                    kelurahan = mRepositoriDokumenViewModel.mDataKelurahan.get(position-1).value.safe()
                    mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
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
                    mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
                }
                else if(position-1 == 1){
                    status = "DIKIRIM"
                    mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
                }
                else if(position-1 == 2){
                    status = "DRAFT"
                    mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
                }
                else if(position-1 == 3){
                    status = "DIKEMBALIKAN"
                        mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
                }
                else if(position-1 == 4){
                    status = "DISETUJUI"
                        mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)
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

        setList()
        mRepositoriDokumenViewModel.getRepositoriDokumen(status,tahun,kelurahan)

        mRepositoriDokumenViewModel.isLoading.observe(this){
            if (it){
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        mRepositoriDokumenViewModel.responseSucces.observe(this){
            setList()
        }

        mRepositoriDokumenViewModel.responseSucces.observe(this){
            setSpinnerTahun()
        }

        mRepositoriDokumenViewModel.responseSucces.observe(this){
            setSpinnerKelurahan()
        }

    }

    fun setSpinnerTahun() {
        val arrayStringTahun = arrayListOf("Pilih Tahun")
        arrayStringTahun.addAll(mRepositoriDokumenViewModel.mDataTahun.map {
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
        arrayStringWilayah.addAll(mRepositoriDokumenViewModel.mDataKota.map {
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
        arrayStringKelurahan.addAll(mRepositoriDokumenViewModel.mDataKelurahan.map {
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
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_repositori)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = RepositoriDokumemAdapter(this,  mRepositoriDokumenViewModel, object:
            RepositoriDokumemAdapter.onItemClickListener {
            override fun onItemClick(position: Int,
                                     nama: String,
                                     nilai: String,
                                     jenisKerjasama: String,
                                     pks: String) {

                val i = Intent(this@RepositoriDokumenActivity, CekDokumenActivity::class.java)
                i.putExtra("Cek Dokumen",mRepositoriDokumenViewModel.mData[position])
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


}
