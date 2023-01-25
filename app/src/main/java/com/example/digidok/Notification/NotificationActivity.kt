package com.example.digidok.Notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.*
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.utils.Preferences

class NotificationActivity : AppCompatActivity() {

    private var recyclerview : RecyclerView ? =null
    lateinit var mNotificationViewModel: NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        supportActionBar?.hide()

        mNotificationViewModel = ViewModelProvider(this@NotificationActivity).get(NotificationViewModel::class.java)
        mNotificationViewModel.token.value = Preferences.isToken(this@NotificationActivity)

        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_notification)
        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Notification")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@NotificationActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, NotificationActivity::class.java))
        }

        mNotificationViewModel.getNotification()
        setList()

        mNotificationViewModel.isLoading.observe(this){
            if (it){
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        mNotificationViewModel.responseSucces.observe(this){
            setList()
        }

    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_notification)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)
        recyclerview?.adapter = NotificationAdapter(this, mNotificationViewModel){

        }

    }

}