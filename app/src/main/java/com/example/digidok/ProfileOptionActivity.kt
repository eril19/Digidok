package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ProfileOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option)

        val header = findViewById<TextView>(R.id.header_title)
        val btn_profile = findViewById<Button>(R.id.btn_profile)

        header.setText("Profile Optioms")

        btn_profile.setOnClickListener {
//            val i = Intent(this@LoginActivity, MenuActivity::class.java)
            startActivity(Intent(this@ProfileOptionActivity, ProfileActivity::class.java))
        }
    }
}