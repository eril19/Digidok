package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DataAsetdikerjasamakanActivity : AppCompatActivity() {

    var isLoading : Boolean = false
    var dataAset: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    var idPksCheck : String = ""
    private var recyclerview: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail2)

        supportActionBar?.hide()
        idPksCheck = intent.getStringExtra("idPks") ?: ""

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, PengajuanKerjasamaActivity::class.java))
            finish()
        }

        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        next_detail_btn.setOnClickListener {
            val i = Intent(this@DataAsetdikerjasamakanActivity, DaftarSuratLampiranActivity::class.java)
            i.putExtra("idPks",idPksCheck)
            startActivity(i)
        }

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
        onBackPressed()
        }

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Detail Pengajuan Kerjasama")

        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        backArrow.setOnClickListener {
            onBackPressed()
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, NotificationActivity::class.java))
        }

        if (!idPksCheck.equals("")){
            getPengajuanKerjasamaDetail(idPksCheck)
        }


        setList()
    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DataAsetdiKerjasamakanAdapter(this, dataAset, object:DataAsetdiKerjasamakanAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
//                TODO("Not yet implemented")
            }

        }){

        }

    }

    fun getPengajuanKerjasamaDetail(idPks:String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDaftarPengajuanKerjasamaDetail(
            token = Preferences.isToken(context = this@DataAsetdikerjasamakanActivity),
            id = idPks,
            object : DataSource.daftarPengajuanDetailCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        dataAset.clear()
                        data.data?.dataAsetDikerjasamakan?.forEach {
                            dataAset?.add(
                                PengajuanKerjasamaDetailModel(
                                    kodeLokasi = it?.kolok.safe(),
                                    namaLokasi = it?.nalok.safe(),
                                    kodeBarang = it?.kobar.safe(),
                                    namaBarang = it?.nabar.safe(),
                                    luas = it?.luas.safe(),
                                    luasManfaat = it?.luasManfaat.safe(),
                                    alamat = it?.keterangan.safe(),
                                )
                            )
                        }
                        setList()
                    }
            }

            override fun onError(message: String) {
//                isLoading = false
                Toast.makeText(this@DataAsetdikerjasamakanActivity, "data gagal", Toast.LENGTH_LONG ).show()
            }

            override fun onFinish() {
                isLoading = false
            }

        })
    }

}