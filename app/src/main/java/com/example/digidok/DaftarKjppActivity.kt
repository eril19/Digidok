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

class DaftarKjppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_daftar_kjpp)

        supportActionBar?.hide()

        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Daftar Kantor Jasa Penilaian Publik")
        header.setTextSize(16F)

        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@DaftarKjppActivity, MenuActivity::class.java)
            startActivity(intent)
        }

        val DaftarKJPPList = listOf<DaftarKjppModel>(
            DaftarKjppModel(
                no_kjpp = "2.08.0001",
                nama_kjpp = "Latief, Hanif dan Rekan",
                telp_kjpp = "021-7198751, 7198752",
//                no = "No.",
                no_perizinan = "760/KM.1/2008",
//                tgl = "Tanggal",
                tgl_perizinan = "2008-11-20",
//                klasifikasi = "Klasifikasi",
                klasifikasi_perizinan = "Properti",
                alamat = "jalan KACE"

            ),
            DaftarKjppModel(
                no_kjpp = "2.08.0002",
                nama_kjpp = "Aditya Iskandar dan Rekan",
                telp_kjpp = "021 25031890",
//                no = "No.",
                no_perizinan = "772/KM.1/2008",
//                tgl = "Tanggal",
                tgl_perizinan = "2008-11-26",
//                klasifikasi = "Klasifikasi",
                klasifikasi_perizinan = "Properti",
                alamat = "jalan KACE"

            ),
            DaftarKjppModel(
                no_kjpp = "2.08.0003",
                nama_kjpp = "Pung's Zulkarnain & Rekan",
                telp_kjpp = "021-230 3840",
//                no = "No.",
                no_perizinan = "798/KM.1/2008",
//                tgl = "Tanggal",
                tgl_perizinan = "2008-12-01",
//                klasifikasi = "Klasifikasi",
                klasifikasi_perizinan = "Properti",
                alamat = "jalan KACE"

            ),
            DaftarKjppModel(
                no_kjpp = "2.08.0004",
                nama_kjpp = "Aditya Masroni Nur RahmanAnizar Hadiyanto dan Rekan",
                telp_kjpp = "021-22868170",
//                no = "No.",
                no_perizinan = "349/KM.1/2020",
//                tgl = "Tanggal",
                tgl_perizinan = "2020-07-20",
//                klasifikasi = "Klasifikasi",
                klasifikasi_perizinan = "Properti",
                alamat = "jalan KACE"

            ),
            DaftarKjppModel(
                no_kjpp = "2.08.0005",
                nama_kjpp = "Stefanus Tonny Hardi & Rekan",
                telp_kjpp = "021- 5637373",
//                no = "No.",
                no_perizinan = "907/KM.1/2008",
//                tgl = "Tanggal",
                tgl_perizinan = "2008-12-23",
//                klasifikasi = "Klasifikasi",
                klasifikasi_perizinan = "Properti dan Bisnis",
                alamat = "jalan KACE"

            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_kjpp)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        recyclerView.adapter = DaftarKjppAdapter(this, DaftarKJPPList,object:DaftarKjppAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val i = Intent(this@DaftarKjppActivity, KjppDetailActivity::class.java)
                i.putExtra("daftarKJPP", DaftarKJPPList[position])
                startActivity(i)
            }

        }){

        }

    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")


}
