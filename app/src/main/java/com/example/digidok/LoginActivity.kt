package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        val login = findViewById<Button>(R.id.loginBtn)

        login.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            startActivity(Intent(this@LoginActivity, NotificationActivity::class.java))
        }
    }
}