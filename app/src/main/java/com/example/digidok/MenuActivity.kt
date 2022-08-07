package com.example.digidok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import meow.bottomnavigation.MeowBottomNavigation

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val bottomNavigation = findViewById<>(R.id.bottomNavigation)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_report))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_dashboard))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_apps))

    }
}