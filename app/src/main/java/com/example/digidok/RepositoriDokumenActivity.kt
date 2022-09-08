package com.example.digidok

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.widget.ArrayAdapter
import com.example.digidok.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RepositoriDokumenActivity : AppCompatActivity() {

    var spinnerTahun : Spinner? = null
    val listTahun = arrayListOf("2022", "2021", "2020")

//    var spinnerWilayah : Spinner? = null
//    val listWilayah = arrayListOf("Wilayah 1", "Wilayah 2", "Wilayah 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_repositori_dokumen)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listTahun)

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
//        spinnerWilayah = findViewById<Spinner>(R.id.spinner_wilayah)
        val header = findViewById<TextView>(R.id.header_title)


        header.setText("Repositori Dokumen")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@RepositoriDokumenActivity, MenuActivity::class.java)
            startActivity(intent)
        }

        //val bg:TextView = findViewById(R.id.header_color)



        val RepositoriList = listOf<RepositoriDokumenModel>(
            RepositoriDokumenModel(
                header_color = "Dikirim",
                jenis_kerjasama = "SEWA",
                no_surat = "21 Tahun 2017",
                nama_mitra = "TRANSPORTASI JAKARTA"
            ),
            RepositoriDokumenModel(
                header_color = "Dikirim",
                jenis_kerjasama = "SEWA",
                no_surat = "21 Tahun 2017",
                nama_mitra = "TRANSPORTASI JAKARTA"
            ),
            RepositoriDokumenModel(
                header_color = "Dikembalikan",
                jenis_kerjasama = "SEWA",
                no_surat = "21 Tahun 2017",
                nama_mitra = "TRANSPORTASI JAKARTA"
            ),
            RepositoriDokumenModel(
                header_color = "Draft",
                jenis_kerjasama = "SEWA",
                no_surat = "21 Tahun 2017",
                nama_mitra = "TRANSPORTASI JAKARTA"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_repositori)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RepositoriDokumemAdapter(this,  RepositoriList){

        }

        setSpinnerKategori()

    }

    fun setSpinnerKategori() {
        val arrayStringTahun = arrayListOf("Pilih Tahun")
        arrayStringTahun.addAll(listTahun)
        spinnerTahun?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringTahun) {
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

//        val arrayStringWilayah = arrayListOf("Pilih Wilayah")
//        arrayStringWilayah.addAll(listWilayah)
//        spinnerTahun?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringWilayah) {
//            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//                return if (convertView != null) {
//                    if (convertView is TextView) {
//                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
//                        try {
//                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
//                        } catch (e: Exception) {
//                            showErrorInflateFont()
//                        }
//                        convertView
//                    } else {
//                        convertView
//                    }
//                } else {
//                    super.getView(position, convertView, parent)
//                }
//            }
//        }
    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")


}
