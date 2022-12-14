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
import com.example.digidok.data.model.daftarKJPPModel
import com.example.digidok.data.model.daftarMitraModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DaftarKjppActivity : AppCompatActivity() {

    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"
    var statusFilter = "1"
    var isLoading : Boolean = false
    var daftarKJPP: ArrayList<DaftarKjppModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_kjpp)

        supportActionBar?.hide()

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Daftar Kantor Jasa Penilaian Publik")
        header.setTextSize(16F)

        val back = findViewById<ImageView>(R.id.backbtn)
        back.setOnClickListener {
            val intent = Intent(this@DaftarKjppActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        setList()
        getKJPP()

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@DaftarKjppActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@DaftarKjppActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@DaftarKjppActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DaftarKjppActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DaftarKjppActivity, NotificationActivity::class.java))
        }

    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_kjpp)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DaftarKjppAdapter(this, daftarKJPP,object:DaftarKjppAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val i = Intent(this@DaftarKjppActivity, KjppDetailActivity::class.java)
                i.putExtra("daftarKJPP", daftarKJPP[position])
                startActivity(i)
            }

        }){

        }

    }


    fun getKJPP() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getKJPP(
            token = Preferences.isToken(context = this@DaftarKjppActivity),
            start = start,
            row = 10,
            order = order,
            sortColumn = sortColumn,
            object : DataSource.KJPPCallback {
                override fun onSuccess(data: BaseApiModel<daftarKJPPModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        daftarKJPP.clear()
                        data.data?.dataKjpp?.forEach {
                            daftarKJPP?.add(
                                DaftarKjppModel(
                                    no_kjpp = it?.noInduk.safe(),
                                    no_perizinan = it?.noIzin.safe(),
                                    nama_kjpp = it?.nama.safe(),
                                    tgl_perizinan = it?.tglIzin.safe(),
                                    alamat = it?.alamat.safe(),
                                    telp_kjpp = it?.phone.safe(),
                                    klasifikasi_perizinan = it?.klasifikasiIzin.safe(),

                                )
                            )
                        }
                        setList()
                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                    Toast.makeText(this@DaftarKjppActivity, message, Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
                    isLoading = false
                }

            })
    }

}
