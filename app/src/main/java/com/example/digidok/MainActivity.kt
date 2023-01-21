package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Login.LoginActivity
import com.example.digidok.utils.Preferences
import android.os.Handler as Handler1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Handler1().postDelayed({
            if(Preferences.isLogin(this@MainActivity)){
                startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
            } else {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }, 2000)

    }
}