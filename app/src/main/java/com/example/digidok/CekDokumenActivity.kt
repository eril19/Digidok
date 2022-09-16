package com.example.digidok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CekDokumenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_cek_dokumen)
    }
}