package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.data.model.dashboardModel
import com.example.digidok.data.model.laporanAsetDikerjasamakanModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe
import org.w3c.dom.Text
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var adapter: DashboardAdapter
    var isLoading : Boolean = false
    var dashboardList: ArrayList<DashboardModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var jml : TextView? = null
    var jmlMitra :TextView? = null
    var jmlNilai :TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val calendar: Calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        val dateText : TextView = findViewById(R.id.dateText)

        supportActionBar?.hide()

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)


      jml = findViewById<TextView>(R.id.jumlah_kerjasama)
        jmlMitra = findViewById<TextView>(R.id.jumlah_mitra)
    jmlNilai = findViewById<TextView>(R.id.jumlah_nilai_kerjasama)
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

        setList()
        getDashboard()

        dropdown_profile.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, ProfileActivity::class.java))
        }

        notifBtn.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,NotificationActivity::class.java))
        }

    }

    fun setList() {
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)
        recyclerview?.adapter = DashboardAdapter(this, dashboardList) {
        }
    }

    fun getDashboard() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDashboard(
            token = Preferences.isToken(context = this@DashboardActivity),
            object : DataSource.dashboardCallback {
                override fun onSuccess(data: BaseApiModel<dashboardModel?>) {
                    isLoading = false

//                    if(data.code == 401){
//                        logout
//                    }

                    if (data.isSuccess) {
//                        var value: Long = 0
                        dashboardList.clear()
//                        value = Integer.parseInt(data.data?.jumlahNilaiKerjasama.safe()).toLong()
                        var formatter :  DecimalFormat = DecimalFormat("#,###")
                        jml?.text = data.data?.jumlahKerjasama.safe()
                        jmlMitra?.text = data.data?.jumlahMitra.safe()
                        jmlNilai?.text = "Rp. " + formatter.format(data.data?.jumlahNilaiKerjasama)
                        data.data?.dataMitra?.forEach {
                            dashboardList?.add(
                                DashboardModel(
                                    nama_mitra = it?.namaMitra.safe(),
                                    jumlah_kerjasama = it?.jumlahKederjasama.safe(),
                                    total_nilai = it?.totalNilai.toString().safe(),
                                    jenis_mitra = it?.idMitra.safe()
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