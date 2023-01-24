package com.example.digidok.DaftarPengajuanKerjasamaDetail2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarPengajuanKerjasama.DaftarPengajuanKerjasamaActivity
import com.example.digidok.DaftarPengajuanKerjasamaDetail3.DaftarSuratLampiranActivity
import com.example.digidok.DaftarPengajuanKerjasamaDetail1.DaftarPengajuanKerjasamaDetailModel
import com.example.digidok.DaftarPengajuanKerjasamaDetail3.DaftarPengajuanKerjasamaDetail3ViewModel
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DataAsetdikerjasamakanActivity : AppCompatActivity() {

    var isLoading : Boolean = false
    var dataAset: ArrayList<DaftarPengajuanKerjasamaDetailModel> = ArrayList()
    var idPksCheck : String = ""
    private var recyclerview: RecyclerView? = null
    lateinit var mDaftarPengajuanKerjasamaDetail2ViewModel: DaftarPengajuanKerjasamaDetail2ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail2)

        supportActionBar?.hide()
        idPksCheck = intent.getStringExtra("idPks") ?: ""

        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)

        mDaftarPengajuanKerjasamaDetail2ViewModel = ViewModelProvider(this@DataAsetdikerjasamakanActivity).get(
            DaftarPengajuanKerjasamaDetail2ViewModel::class.java)
        mDaftarPengajuanKerjasamaDetail2ViewModel.token.value = Preferences.isToken(this@DataAsetdikerjasamakanActivity)

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, DaftarPengajuanKerjasamaActivity::class.java))
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
            mDaftarPengajuanKerjasamaDetail2ViewModel.getPengajuanKerjasamaDetail(idPksCheck)
        }

        setList()

        mDaftarPengajuanKerjasamaDetail2ViewModel.isLoading.observe(this){
            if (it){
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        mDaftarPengajuanKerjasamaDetail2ViewModel.responseSucces.observe(this){
            setList()
        }

    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DataAsetdiKerjasamakanAdapter(this, mDaftarPengajuanKerjasamaDetail2ViewModel, object:
            DataAsetdiKerjasamakanAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
//                TODO("Not yet implemented")
            }

        }){

        }

    }


}