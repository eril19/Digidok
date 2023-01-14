package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.dashboardModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe
import java.text.DecimalFormat

class DaftarTerbaruKerjasamaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_terbaru_kerjasama)

        supportActionBar?.hide()

        recyclerview = findViewById(R.id.rv_list_dashboard)

        setList()
        getDashboard()

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Daftar Terbaru Kerjasama")
        header.setTextSize(16F)

        val back = findViewById<ImageView>(R.id.backbtn)
        back.setOnClickListener {
            val intent = Intent(this@DaftarTerbaruKerjasamaActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@DaftarTerbaruKerjasamaActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@DaftarTerbaruKerjasamaActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@DaftarTerbaruKerjasamaActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DaftarTerbaruKerjasamaActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DaftarTerbaruKerjasamaActivity, NotificationActivity::class.java))
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
            token = Preferences.isToken(context = this@DaftarTerbaruKerjasamaActivity),
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
                        var formatter : DecimalFormat = DecimalFormat("#,###")
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
                    Toast.makeText(this@DaftarTerbaruKerjasamaActivity, message, Toast.LENGTH_LONG).show()
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