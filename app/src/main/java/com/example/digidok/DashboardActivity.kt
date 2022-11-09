package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import org.w3c.dom.Text
import java.text.DateFormat
import java.util.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var adapter: DashboardAdapter
    var isLoading : Boolean = false
    var dashboardList: ArrayList<DashboardModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val calendar: Calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        val dateText : TextView = findViewById(R.id.dateText)

        supportActionBar?.hide()

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)

        val namaRole = findViewById<TextView>(R.id.namaRole)
        val namaUser = findViewById<TextView>(R.id.namaUser)
        val notifBtn:ImageView = findViewById(R.id.notificationbtn)
        val dropdown_profile: ImageView = findViewById(R.id.profileArrow)
        val dokumenBtn : ImageButton = findViewById(R.id.repositoriDokumenBtn)


        namaUser.text = Preferences.isUser(this@DashboardActivity)
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

        setListData()
        getBerita()

        dropdown_profile.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ProfileActivity::class.java))
        }

        notifBtn.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,NotificationActivity::class.java))
        }

    }

    fun setListData() {
//        val sizeofData : TextView = v.findViewById(R.id.dataSize)


        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

//        val showData = DashboardList.size
//        sizeofData.setText(showData)
        recyclerview?.adapter = DashboardAdapter(this, dashboardList) {
        }
    }

    fun getBerita() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "5",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    dashboardList.clear()
                    data.rows?.forEach {
                        dashboardList?.add(DashboardModel(it?.editor.toString(), it?.tanggal.toString()))
                    }
                    setListData()
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