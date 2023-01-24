package com.example.digidok.LaporanAsetDikerjasamakanDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.LaporanAsetDikerjasamakan.LaporanAsetKerjasamaModel
import com.example.digidok.LaporanAsetDikerjasamakan.LaporanAsetKerjasamaViewModel
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import java.text.DecimalFormat

class LaporanAsetDetailActivity : AppCompatActivity() {
    lateinit var mLaporanAsetKerjasamaViewModel: LaporanAsetKerjasamaViewModel
    private var recyclerview: RecyclerView? = null
    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"
    var data: LaporanAsetKerjasamaModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_aset_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("laporanAset")


        val tutup = findViewById<TextView>(R.id.close_detail_btn)
        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        val id_pks = findViewById<TextView>(R.id.nomerpks)
        val nama_mitra = findViewById<TextView>(R.id.namamitra)
        val nilai_pks = findViewById<TextView>(R.id.hargapks)
        val jenis_kerjasama = findViewById<TextView>(R.id.jeniskerjasama)
        var formatter : DecimalFormat = DecimalFormat("#,###")

        nama_mitra.text = data?.nama_mitra
        id_pks.text = data?.id_pks
        nilai_pks.text = "Rp. " + formatter.format(data?.nilai_pks?.toLong())
        jenis_kerjasama.text =  data?.jenis_kerjasama

        tutup.setOnClickListener {
            onBackPressed()
        }
        backArrow.setOnClickListener {
            onBackPressed()
        }

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Detail Laporan Aset Dikerjasamakan")

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@LaporanAsetDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@LaporanAsetDetailActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@LaporanAsetDetailActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@LaporanAsetDetailActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@LaporanAsetDetailActivity, NotificationActivity::class.java))
        }

        setList()
        mLaporanAsetKerjasamaViewModel.getLaporanAsetDetail()
    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_laporan_aset_detail)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = LaporanAsetDetailAdapter(this,  mLaporanAsetKerjasamaViewModel){

        }
    }

}