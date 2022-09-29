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

class PengajuanKerjasamaActivity : AppCompatActivity() {

    var spinnerStatus : Spinner? = null
    val listStatus = arrayListOf("SEMUA", "DRAFT", "MENUNGGU VALIDASI", "DISETUJUI")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_pengajuan_kerjasama)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listStatus)

        supportActionBar?.hide()

        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Daftar Pengajuan Kerjasama")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@PengajuanKerjasamaActivity, MenuActivity::class.java)
            startActivity(intent)
        }

        //val bg:TextView = findViewById(R.id.header_color)



        val PengajuanKerjasamaList = listOf<PengajuanKerjasamaModel>(
            PengajuanKerjasamaModel(
                header_color = "Draft",
                id_mitra = "MT-2000-0001",
                nama_mitra = "PT INDOCATER",
                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
                status_mitra = "whitelist",
//                npwp = "NPWP",
                npwp_mitra = "02.623.519.2-061.000",
            ),
            PengajuanKerjasamaModel(
                header_color = "Dikirim",
                id_mitra = "MT-2011-0001",
                nama_mitra = "PT Wahana Nusantara",
                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
                status_mitra = "whitelist",
//                npwp = "NPWP",
                npwp_mitra = "013121280073000",
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_pengajuan_kerjasama)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = PengajuanKerjasamaAdapter(this, PengajuanKerjasamaList,object : PengajuanKerjasamaAdapter.onItemClickListener{

            override fun onItemClick(position: Int) {
                val i = Intent(this@PengajuanKerjasamaActivity,PengajuanKerjasamaDetailActivity::class.java)

                startActivity(i)
            }

        }){

        }

        setSpinnerKategori()

    }

    fun setSpinnerKategori() {
        val arrayString = arrayListOf("Pilih Status")
        arrayString.addAll(listStatus)
        spinnerStatus?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayString) {
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
