package com.example.digidok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_option)

        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Profile Optioms")
    }
}