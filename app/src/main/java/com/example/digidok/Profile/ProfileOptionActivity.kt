package com.example.digidok.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.digidok.R

class ProfileOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option)

        supportActionBar?.hide()

        val header = findViewById<TextView>(R.id.header_title)
        val btn_profile = findViewById<ImageButton>(R.id.btnToProfile)
        val logout = findViewById<ImageButton>(R.id.logoutbtn)
        val back = findViewById<ImageView>(R.id.backbtn)



        back.setOnClickListener {
//            val intent = Intent(this@ProfileOptionActivity, MenuActivity::class.java)
//            startActivity(intent)
        }

        header.setText("Profile Options")

        btn_profile.setOnClickListener {
//            val i = Intent(this@LoginActivity, MenuActivity::class.java)
            startActivity(Intent(this@ProfileOptionActivity, ProfileActivity::class.java))
        }
    }
}