package com.example.digidok

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat

class PengajuanKerjasamaDetailActivity3 : AppCompatActivity() {

    var hideTelaah : String = ""
    var data : PengajuanKerjasamaModel? = null
    var spinnerTelaah : Spinner? = null
    val listTelaah = arrayListOf("Disetujui", "Dikembalikan", "Ditolak")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail3)

        supportActionBar?.hide()

        data = intent.getParcelableExtra("PengajuanKerjasama")
        hideTelaah = intent.getStringExtra("status")?:""

        var menuTelaah = findViewById<LinearLayout>(R.id.menu_telaah)

        if(hideTelaah.equals("Telaah",true)){
            menuTelaah.visibility = View.VISIBLE
        } else {
            menuTelaah.visibility = View.VISIBLE
        }


        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity3, PengajuanKerjasamaActivity::class.java))
            finish()
        }

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity3, PengajuanKerjasamaDetailActivity2::class.java))
        }

        spinnerTelaah = findViewById<Spinner>(R.id.spinner_telaah)
        setSpinnerKategori()
    }

    fun setSpinnerKategori() {
        val arrayString = arrayListOf("Pilih hasil telaah")
        arrayString.addAll(listTelaah)
        spinnerTelaah?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayString) {
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