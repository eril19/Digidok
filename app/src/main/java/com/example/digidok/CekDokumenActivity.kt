package com.example.digidok

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.data.model.laporanAsetDikerjasamakanModel
import com.example.digidok.data.model.repositoriDokumenModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class CekDokumenActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var cekDokumen: ArrayList<CekDokumenModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var data:RepositoriDokumenModel? = null
    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_dokumen)

        supportActionBar?.hide()
        data = intent.getParcelableExtra("Cek Dokumen")

        val nama = findViewById<TextView>(R.id.namamitradokumen)
        val nomer = findViewById<TextView>(R.id.nomerpksdokumen)
        val jenis = findViewById<TextView>(R.id.jeniskerjadokumen)
        val harga = findViewById<TextView>(R.id.hargadokumen)


        nama.setText(data?.nama_mitra)
        nomer.setText(data?.no_surat)
        harga.setText(data?.harga)
        jenis.setText(data?.jenis_kerjasama)

        val tutup = findViewById<TextView>(R.id.close_detail_btn)
        val backArrow = findViewById<ImageButton>(R.id.backbtn)

        tutup.setOnClickListener {
            onBackPressed()
        }

        backArrow.setOnClickListener {
            onBackPressed()
        }

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Cek Dokumen")

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@CekDokumenActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@CekDokumenActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@CekDokumenActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@CekDokumenActivity, NotificationActivity::class.java))
        }

        setList()
        getCekDokumen()
    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_cek_dokumen)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = CekDokumenAdapter(this,  cekDokumen, object : CekDokumenAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemClickCekLampiran(position: Int, link: String) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setDataAndType(Uri.parse(link), "application/pdf");
                    startActivity(intent)
                }catch (e : Exception){
                    Toast.makeText(this@CekDokumenActivity, "Tidak ada aplikasi untuk menampilkan file PDF", Toast.LENGTH_SHORT).show()
                }
            }
        }){

        }
    }

    fun getCekDokumen() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getRepositoriDokumen(
            token = Preferences.isToken(context = this@CekDokumenActivity),
            start = start,
            row = 10,
            order = order,
            sortColumn = sortColumn,
            search = "",
            statusFilter = "SEMUA",
            tahunFilter = 2017,
            kelurahanFilter = "",
            object : DataSource.repositoriDokumenCallback {
                override fun onSuccess(data: BaseApiModel<repositoriDokumenModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        cekDokumen.clear()
                        data.data?.dataDokumen?.forEach {
                            it?.dataLampiran?.forEach{
                                cekDokumen?.add(
                                    CekDokumenModel(
                                        header_color = "",
                                        nama_dokumen = it?.label.safe(),
                                        file = it?.file.safe()
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