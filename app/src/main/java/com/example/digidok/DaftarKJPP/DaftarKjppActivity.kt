package com.example.digidok.DaftarKJPP

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.*
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.utils.Preferences

class DaftarKjppActivity : AppCompatActivity() {

    private var recyclerview: RecyclerView? = null
    lateinit var mDaftarKjppViewModel: DaftarKjppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_kjpp)

        supportActionBar?.hide()

        mDaftarKjppViewModel = ViewModelProvider(this@DaftarKjppActivity).get(DaftarKjppViewModel::class.java)
        mDaftarKjppViewModel.token.value = Preferences.isToken(this@DaftarKjppActivity)
        mDaftarKjppViewModel.row.value = "10"
        mDaftarKjppViewModel.order.value = "asc"
        mDaftarKjppViewModel.start.value = "0"
        mDaftarKjppViewModel.sortColumn.value = "no"


        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Daftar Kantor Jasa Penilaian Publik")
        header.setTextSize(16F)

        val back = findViewById<ImageView>(R.id.backbtn)
        back.setOnClickListener {
            val intent = Intent(this@DaftarKjppActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }


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

        mDaftarKjppViewModel.getKJPP()
        setList()
        observeViewModel()
    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_kjpp)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DaftarKjppAdapter(this, mDaftarKjppViewModel,object:
            DaftarKjppAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val i = Intent(this@DaftarKjppActivity, KjppDetailActivity::class.java)
                i.putExtra("daftarKJPP", mDaftarKjppViewModel.mData[position])
                startActivity(i)
            }

        }){

        }

    }

    private fun observeViewModel() {
        mDaftarKjppViewModel.mMessageResponse.observe(this){
            Toast.makeText(this@DaftarKjppActivity, it, Toast.LENGTH_LONG).show()
        }

        mDaftarKjppViewModel.start.observe(this){
            setList()
        }
    }


}
