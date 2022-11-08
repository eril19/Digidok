package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.utils.Injection

class LaporanAsetDetailActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var laporanAsetDetail: ArrayList<LaporanAsetDetailModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var data:LaporanAsetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_aset_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("laporanAset")

        val tutup = findViewById<TextView>(R.id.close_detail_btn)
        val id_pks = findViewById<TextView>(R.id.nomerpks)
        val nama_mitra = findViewById<TextView>(R.id.namamitra)
        val nilai_pks = findViewById<TextView>(R.id.hargapks)
        val jenis_kerjasama = findViewById<TextView>(R.id.jeniskerjasama)

//        val LaporanAsetDetail = listOf<LaporanAsetDetailModel>(
//            LaporanAsetDetailModel(
//                num_laporan = "#1",
//                kode_barang = "131010104001::000001",
//                nama_bmd = "Tanah Bangunan Kantor Pemerintah",
//                kode_lokasi = "00392",
//                nama_lokasi = "PUSAT PENYIMPANAN BARANG DAERAH",
//                luas_bmd = "15855 M2",
//                keterangan_bmd = "Jl. Perintis Kemerdekaan No. 1"
//            )
//        )


        tutup.setOnClickListener {
//            val i = Intent(this@LaporanAsetDetailActivity, LaporanAsetActivity::class.java)
//            startActivity(i)
            onBackPressed()
        }


        id_pks.setText(data?.id_pks)
        nama_mitra.setText(data?.nama_mitra)
        nilai_pks.setText(data?.nilai_pks)
        jenis_kerjasama.setText(data?.jenis_kerjasama)

        setList()
        getLaporanAsetDetail()
    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_laporan_aset_detail)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = LaporanAsetDetailAdapter(this,  laporanAsetDetail){

        }
    }

    fun getLaporanAsetDetail() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "12",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    laporanAsetDetail.clear()
                    data.data?.forEach {
                        laporanAsetDetail?.add(
                            LaporanAsetDetailModel(
                                num_laporan = "#1",
                                kode_barang = "131010104001::000001",
                                nama_bmd = "Tanah Bangunan Kantor Pemerintah",
                                kode_lokasi = "00392",
                                nama_lokasi = "PUSAT PENYIMPANAN BARANG DAERAH",
                                luas_bmd = "15855 M2",
                                keterangan_bmd = "Jl. Perintis Kemerdekaan No. 1"
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