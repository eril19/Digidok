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

class LaporanAsetActivity : AppCompatActivity() {

    var spinnerTahun : Spinner? = null
    val listTahun = arrayListOf("2022", "2021", "2020")

//    var spinnerWilayah : Spinner? = null
//    val listWilayah = arrayListOf("Wilayah 1", "Wilayah 2", "Wilayah 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_laporan_aset)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listTahun)

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
//        spinnerWilayah = findViewById<Spinner>(R.id.spinner_wilayah)
        val header = findViewById<TextView>(R.id.header_title)


        header.setText("Laporan Aset Dikerjasamakan")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@LaporanAsetActivity, MenuActivity::class.java)
            startActivity(intent)
        }

        //val bg:TextView = findViewById(R.id.header_color)



        val LaporanAset = listOf<LaporanAsetModel>(
            LaporanAsetModel(
                header_color = "Dikirim",
                id_pks = "PKS-2022-000001",
                nama_mitra = "TRANSPORTASI JAKARTA",
                nilai_pks = "Rp. 17,687,975,000",
                jenis_kerjasama = "SEWA"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_laporan_aset)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = LaporanAsetAdapter(this,  LaporanAset){

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
