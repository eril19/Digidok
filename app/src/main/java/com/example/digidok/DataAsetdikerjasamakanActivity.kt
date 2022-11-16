package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DataAsetdikerjasamakanActivity : AppCompatActivity() {

    var isLoading : Boolean = false
    var dataAset: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    var idPksCheck : String = ""
    private var recyclerview: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail2)

        supportActionBar?.hide()

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@DataAsetdikerjasamakanActivity, PengajuanKerjasamaActivity::class.java))
            finish()
        }

        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        next_detail_btn.setOnClickListener {
            val i = Intent(this@DataAsetdikerjasamakanActivity, DaftarSuratLampiranActivity::class.java)
//            i.putExtra("hideTelaah",true)
            startActivity(i)
        }

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
//            startActivity(Intent(this@PengajuanKerjasamaDetailActivity2, PengajuanKerjasamaDetailActivity::class.java))
        onBackPressed()
        }


        idPksCheck = intent.getStringExtra("idPks") ?: ""



        setList()
        getPengajuanKerjasamaDetail()
    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DataAsetdiKerjasamakanAdapter(this, dataAset, object:DataAsetdiKerjasamakanAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
//                TODO("Not yet implemented")
            }

        }){

        }

    }

    fun getPengajuanKerjasamaDetail(idPks:String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDaftarPengajuanKerjasamaDetail(
            token = Preferences.isToken(context = this@DataAsetdikerjasamakanActivity),
            id = idPks,
            object : DataSource.daftarPengajuanDetailCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        dataAset.clear()
                        data.data?.dataAsetDikerjasamakan?.forEach {
                            dataAset?.add(
                                PengajuanKerjasamaDetailModel(
                                    kodeLokasi = it?.kolok.safe(),
                                    namaLokasi = it?.nalok.safe(),
                                    kodeBarang = it?.kobar.safe(),
                                    namaBarang = it?.nabar.safe(),
                                    npwp_mitra = it?.npwp.safe(),
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