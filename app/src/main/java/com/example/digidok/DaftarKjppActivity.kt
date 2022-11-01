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
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.utils.Injection

class DaftarKjppActivity : AppCompatActivity() {

    var isLoading : Boolean = false
    var daftarKJPP: ArrayList<DaftarKjppModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

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
//            val intent = Intent(this@DaftarKjppActivity, DashboardActivity::class.java)
//            startActivity(intent)
            onBackPressed()
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

        setList()
        getDaftarKJPP()

    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_kjpp)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DaftarKjppAdapter(this, daftarKJPP,object:DaftarKjppAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val i = Intent(this@DaftarKjppActivity, KjppDetailActivity::class.java)
                i.putExtra("daftarKJPP", daftarKJPP[position])
                startActivity(i)
            }

        }){

        }

    }

    fun getDaftarKJPP() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "10",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    daftarKJPP.clear()
                    data.rows?.forEach {
                        daftarKJPP?.add(
                            DaftarKjppModel(
                                no_kjpp = "2.08.0005",
                                nama_kjpp = "Stefanus Tonny Hardi & Rekan",
                                telp_kjpp = "021- 5637373",
                                no_perizinan = "907/KM.1/2008",
                                tgl_perizinan = "2008-12-23",
                                klasifikasi_perizinan = "Properti dan Bisnis",
                                alamat = "jalan KACE"

                            )
                        )
                    }
                    setList()
                }
            }

            override fun onError(message: String) {
                isLoading = false
            }

            override fun onFinish() {
                isLoading = false
            }

        })
    }

}
