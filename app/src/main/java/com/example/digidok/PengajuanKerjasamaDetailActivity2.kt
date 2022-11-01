package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.utils.Injection
import org.w3c.dom.Text

class PengajuanKerjasamaDetailActivity2 : AppCompatActivity() {
    var isLoading : Boolean = false
    var pengajuanKerjasamaDetail: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    var data : PengajuanKerjasamaModel ?= null
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail2)

        supportActionBar?.hide()

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaDetailActivity2, PengajuanKerjasamaActivity::class.java))
            finish()
        }

        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        next_detail_btn.setOnClickListener {
            val i = Intent(this@PengajuanKerjasamaDetailActivity2, PengajuanKerjasamaDetailActivity3::class.java)
//            i.putExtra("hideTelaah",true)
            startActivity(i)
        }

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
//            startActivity(Intent(this@PengajuanKerjasamaDetailActivity2, PengajuanKerjasamaDetailActivity::class.java))
        onBackPressed()
        }



        data = intent.getParcelableExtra("PengajuanKerjasama")
//        samain sama i.putExtra!

        setList()
        getPengajuanDetail()
    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = PengajuanKerjasamaDetailAdapter(this, pengajuanKerjasamaDetail, object:PengajuanKerjasamaDetailAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }

        }){

        }

    }

    fun getPengajuanDetail() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "5",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    pengajuanKerjasamaDetail.clear()
                    data.rows?.forEach {
                        pengajuanKerjasamaDetail?.add(
                            PengajuanKerjasamaDetailModel(
                                kodeLokasi = "0089879000",
                                namaLokasi  = "Metro Jaya",
                                kodeBarang  = "0009-0989-0087",
                                namaBarang  = "Rumah",
                                alamat  = "JL. Kundur Jaya",
                                Luas  = "2000m2",
                                LuasManfaat  = "1500m2"
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