package com.example.digidok.Dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.*
import com.example.digidok.DaftarKJPP.DaftarKjppActivity
import com.example.digidok.DaftarMitra.DaftarMitraActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.RepositoriDokumen.RepositoriDokumenActivity
import com.example.digidok.data.Repository
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import java.text.DateFormat
import java.util.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var adapter: DashboardAdapter
    var isLoading : Boolean = false
    var namaUser : TextView ?= null
    var dashboardList: ArrayList<DashboardModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var jml : TextView? = null
    var jmlMitra :TextView? = null
    var jmlNilai :TextView? = null
    lateinit var mDashboardViewModel: DashboardViewModel

    val mRepository: Repository = Injection.provideRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mDashboardViewModel = ViewModelProvider(this@DashboardActivity).get(DashboardViewModel::class.java)
        mDashboardViewModel.token.value = Preferences.isToken(this@DashboardActivity)


        val calendar: Calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        val dateText : TextView = findViewById(R.id.dateText)

        supportActionBar?.hide()

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)

        jml = findViewById<TextView>(R.id.jumlah_kerjasama)
        jmlMitra = findViewById<TextView>(R.id.jumlah_mitra)
        jmlNilai = findViewById<TextView>(R.id.jumlah_nilai_kerjasama)
        val namaRole = findViewById<TextView>(R.id.namaRole)
        namaUser = findViewById<TextView>(R.id.namaUser)
        val dokumenBtn : ImageButton = findViewById(R.id.repositoriDokumenBtn)


        namaUser = findViewById(R.id.namaUser)
        namaRole.text = Preferences.Role(this@DashboardActivity)

        dokumenBtn.setOnClickListener {
            val i : Intent = Intent(this@DashboardActivity, RepositoriDokumenActivity::class.java)
            startActivity(i)
        }

        val kerjasamaBtn : ImageButton = findViewById(R.id.kerjasamaBtn)
        kerjasamaBtn.setOnClickListener {
            val i : Intent = Intent(this@DashboardActivity, LaporanPengajuanActivity::class.java)
            startActivity(i)
        }

        val laporanAsetBtn : ImageButton = findViewById(R.id.laporanAsetBtn)
        laporanAsetBtn.setOnClickListener {
            val i : Intent = Intent(this@DashboardActivity, LaporanAsetActivity::class.java)
            startActivity(i)
        }

        val daftarmitrabtn : ImageButton = findViewById(R.id.mitraBtn)
        daftarmitrabtn.setOnClickListener {
            val i : Intent = Intent(this@DashboardActivity, DaftarMitraActivity::class.java)
            startActivity(i)
        }

        val pengajuanKerjasamaabtn : ImageButton = findViewById(R.id.pengajuanBtn)
        pengajuanKerjasamaabtn.setOnClickListener {
            val i : Intent = Intent(this@DashboardActivity, PengajuanKerjasamaActivity::class.java)
            startActivity(i)
        }

        val daftarKjppbtn : ImageButton = findViewById(R.id.kjppBtn)
        daftarKjppbtn.setOnClickListener {
            val i : Intent = Intent(this@DashboardActivity, DaftarKjppActivity::class.java)
            startActivity(i)
        }

        recyclerview = findViewById(R.id.rv_list_dashboard)

        dateText.setText(currentDate)

        setList()
        mDashboardViewModel.getDashboard()
        mDashboardViewModel.getProfileData()
        observeViewModel()

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, NotificationActivity::class.java))
        }

        val viewAllBtn : TextView = findViewById(R.id.view_all)
        viewAllBtn.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, DaftarTerbaruKerjasamaActivity::class.java))
        }

    }

    fun setList() {
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)
        recyclerview?.adapter = DashboardAdapter(this, mDashboardViewModel) {
        }
    }

    private fun observeViewModel() {
        mDashboardViewModel.mMessageResponse.observe(this){
            Toast.makeText(this@DashboardActivity, it, Toast.LENGTH_LONG).show()
        }

        mDashboardViewModel.username.observe(this){
            namaUser?.text = it
        }

        mDashboardViewModel.jml.observe(this){
            jml?.text = mDashboardViewModel.jml.value
            jmlMitra?.text = mDashboardViewModel.jmlMitra.value
            jmlNilai?.text = mDashboardViewModel.jmlNilai.value
            setList()
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance() =
            DashboardActivity.apply {
               Bundle().apply {
                }
            }
    }
}