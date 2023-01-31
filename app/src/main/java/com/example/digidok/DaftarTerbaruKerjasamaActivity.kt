package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Dashboard.DashboardAdapter
import com.example.digidok.Dashboard.DashboardViewModel
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.utils.Preferences

class DaftarTerbaruKerjasamaActivity : AppCompatActivity() {

    lateinit var mDashboardViewModel: DashboardViewModel
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_terbaru_kerjasama)

        supportActionBar?.hide()

        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_dashboard)

        mDashboardViewModel = ViewModelProvider(this@DaftarTerbaruKerjasamaActivity).get(
            DashboardViewModel::class.java)
        mDashboardViewModel.token.value = Preferences.isToken(this@DaftarTerbaruKerjasamaActivity)

        recyclerview = findViewById(R.id.rv_list_dashboard)

        setList()
        mDashboardViewModel.getDashboard()

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

        mDashboardViewModel.isLoading.observe(this){
            if (it){
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        mDashboardViewModel.responseSucces.observe(this){
            setList()
        }

    }

    fun setList() {
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)
        recyclerview?.adapter = DashboardAdapter(this, mDashboardViewModel) {
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