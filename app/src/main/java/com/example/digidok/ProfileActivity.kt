package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
        val header = findViewById<TextView>(R.id.header_title)

        val back = findViewById<ImageView>(R.id.backbtn)



        back.setOnClickListener {
            val intent = Intent(this@ProfileActivity, MenuActivity::class.java)
            startActivity(intent)
        }
        header.setText("Profile")
    }
}