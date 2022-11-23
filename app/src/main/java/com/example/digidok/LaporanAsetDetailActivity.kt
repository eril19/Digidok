package com.example.digidok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.laporanAsetDikerjasamakanModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class LaporanAsetDetailActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var laporanAsetDetail: ArrayList<LaporanAsetDetailModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"
    var data:LaporanAsetModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_aset_detail)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("laporanAset")


        val tutup = findViewById<TextView>(R.id.close_detail_btn)
        val backArrow = findViewById<ImageButton>(R.id.backArrowBtn)
        val id_pks = findViewById<TextView>(R.id.nomerpks)
        val nama_mitra = findViewById<TextView>(R.id.namamitra)
        val nilai_pks = findViewById<TextView>(R.id.hargapks)
        val jenis_kerjasama = findViewById<TextView>(R.id.jeniskerjasama)

        nama_mitra.text = data?.nama_mitra
        id_pks.text = data?.id_pks
        nilai_pks.text = "Rp. " + data?.nilai_pks
        jenis_kerjasama.text = data?.jenis_kerjasama

        tutup.setOnClickListener {
            onBackPressed()
        }
        backArrow.setOnClickListener {
            onBackPressed()
        }


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
        mRepository.getLaporanAsetDikerjasamakan(
            token = Preferences.isToken(context = this@LaporanAsetDetailActivity),
            start = start,
            row = 10,
            order = order,
            sortColumn = sortColumn,
            search = "",
            statusFilter = "SEMUA",
            tahunFilter = 2017,
            kelurahanFilter = "",
            object : DataSource.laporanAsetDikerjasamakanCallback {
                override fun onSuccess(data: BaseApiModel<laporanAsetDikerjasamakanModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        laporanAsetDetail.clear()
                        data.data?.dataDokumen?.forEach {
                                it?.dataDetail?.forEach{
                                laporanAsetDetail?.add(
                                LaporanAsetDetailModel(
                                    kode_barang = it?.kobar.safe(),
                                    nama_bmd = it?.nabar.safe(),
                                    kode_lokasi = it?.kolok.safe(),
                                    nama_lokasi = it?.nalok.safe(),
                                    luas_bmd = it?.luas.safe(),
                                    keterangan_bmd = it?.keterangan.safe(),
                                )
                            )
                        setList()
                        }
                    }
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