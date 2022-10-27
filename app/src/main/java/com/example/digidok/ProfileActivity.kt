package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.digidok.utils.Preferences

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
        val header = findViewById<TextView>(R.id.header_title)
        val logoout = findViewById<Button>(R.id.logout)
        val back = findViewById<ImageView>(R.id.backbtn)

        logoout.setOnClickListener {
            Preferences.saveLogin(this@ProfileActivity, false)
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }

        back.setOnClickListener {
            val intent = Intent(this@ProfileActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
        header.setText("Profile")
    }
}