package com.example.digidok

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat

class MitraDetailActivity2 : AppCompatActivity() {

    var spinner_jenis_mitra : Spinner? = null
    val listJenisMitra = arrayListOf("Perorangan", "BUMD", "BUMN", "Perusahaan Swasta", "Yayasan / Foundation")
    var isEdit :String = ""
    var spinner_status_mitra : Spinner? = null
    val listStatusMitra = arrayListOf("Whitelist", "Redlist", "Blacklist")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitra_detail2)

        supportActionBar?.hide()
        isEdit = intent.getStringExtra("menu2" )?:""
        spinner_jenis_mitra = findViewById<Spinner>(R.id.spinner_jenis_mitra)
        spinner_status_mitra = findViewById<Spinner>(R.id.spinner_status_mitra)

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
//            startActivity(Intent(this@MitraDetailActivity2, MitraDetailActivity::class.java))
            onBackPressed()
        }

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@MitraDetailActivity2, DaftarMitraActivity::class.java))
            finish()
        }

        val simpan = findViewById<Button>(R.id.save_detail_mitra_btn)
        val tahunGabung = findViewById<EditText>(R.id.tahun_gabung)
        val jenisMitra = findViewById<Spinner>(R.id.spinner_jenis_mitra)
        val statusMitra = findViewById<Spinner>(R.id.spinner_status_mitra)

        if (isEdit.equals("View", true)){
            simpan.visibility = View.GONE
            tahunGabung.isEnabled = false
            tahunGabung.background = ContextCompat.getDrawable(tahunGabung.context, R.drawable.custom_profile)
            jenisMitra.isClickable = false
            jenisMitra.isEnabled = false
            jenisMitra.background = ContextCompat.getDrawable(jenisMitra.context, R.drawable.custom_profile)
            statusMitra.isClickable = false
            statusMitra.isEnabled = false
            statusMitra.background = ContextCompat.getDrawable(statusMitra.context, R.drawable.custom_profile)
        }

        setSpinnerKategori()
    }

    fun setSpinnerKategori() {
        val arrayStringTahun = arrayListOf("Pilih Jenis Mitra")
        arrayStringTahun.addAll(listJenisMitra)
        spinner_jenis_mitra?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringTahun) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
                        } catch (e: Exception) {
                            showErrorInflateFont()
                        }
                        convertView
                    } else {
                        convertView
                    }
                } else {
                    super.getView(position, convertView, parent)
                }
            }
        }

        val arrayStringWilayah = arrayListOf("Pilih Status")
        arrayStringWilayah.addAll(listStatusMitra)
        spinner_status_mitra?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringWilayah) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
                        } catch (e: Exception) {
                            showErrorInflateFont()
                        }
                        convertView
                    } else {
                        convertView
                    }
                } else {
                    super.getView(position, convertView, parent)
                }
            }
        }

    }
    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")
}