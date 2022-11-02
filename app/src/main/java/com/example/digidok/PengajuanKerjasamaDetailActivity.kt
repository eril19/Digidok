package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.utils.Injection
import org.w3c.dom.Text

class PengajuanKerjasamaDetailActivity : AppCompatActivity() {
    var isStatusEdit: String = ""
    var isLoading: Boolean = false
    var pengajuanKerjasamaDetail: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    var data: PengajuanKerjasamaModel? = null
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail)

        supportActionBar?.hide()

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        val no_pengajuan = findViewById<EditText>(R.id.no_pengajuan)
        val nama_mitra = findViewById<EditText>(R.id.nama_mitra)
        val ETtujuan = findViewById<EditText>(R.id.tujuan)
        val no_Surat = findViewById<EditText>(R.id.no_surat)
        val tgl_Surat = findViewById<EditText>(R.id.tgl_surat)
        val Objek = findViewById<EditText>(R.id.objek)
        val tgl_mulai = findViewById<EditText>(R.id.tgl_mulai)
        val nilai_ = findViewById<EditText>(R.id.nilai)
        val tgl_akhir = findViewById<EditText>(R.id.tgl_akhir)
        val prihal = findViewById<EditText>(R.id.perihal)
        val skema = findViewById<EditText>(R.id.skema_pemanfaatan)

        isStatusEdit = intent.getStringExtra("status") ?: ""

        if (isStatusEdit.equals("Edit", true)) {
            no_pengajuan.isEnabled = true
            no_pengajuan.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            nama_mitra.isEnabled = true
            nama_mitra.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            ETtujuan.isEnabled = true
            ETtujuan.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            no_Surat.isEnabled = true
            no_Surat.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            tgl_akhir.isEnabled = true
            tgl_akhir.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            tgl_mulai.isEnabled = true
            tgl_mulai.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            tgl_Surat.isEnabled = true
            tgl_Surat.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            prihal.isEnabled = true
            prihal.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            nilai_.isEnabled = true
            nilai_.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            Objek.isEnabled = true
            Objek.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)

            skema.isEnabled = true
            skema.background =
                ContextCompat.getDrawable(no_pengajuan.context, R.drawable.custom_profile_enable)
        } else {
            no_pengajuan.isEnabled = false
            skema.isEnabled = false
            nama_mitra.isEnabled = false
            ETtujuan.isEnabled = false
            no_Surat.isEnabled = false
            tgl_akhir.isEnabled = false
            tgl_mulai.isEnabled = false
            tgl_Surat.isEnabled = false
            prihal.isEnabled = false
            nilai_.isEnabled = false
            Objek.isEnabled = false
        }


        close_detail_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@PengajuanKerjasamaDetailActivity,
                    PengajuanKerjasamaActivity::class.java
                )
            )
            finish()
        }

        next_detail_btn.setOnClickListener {
            val i = Intent(
                this@PengajuanKerjasamaDetailActivity,
                PengajuanKerjasamaDetailActivity2::class.java
            )
            i.putExtra("hideTelaah", true)
            startActivity(i)
        }

        val nomerPengajuan = findViewById<TextView>(R.id.no_pengajuan)
        val namaMitra = findViewById<TextView>(R.id.nama_mitra)
        val skemaPemanfaatan = findViewById<TextView>(R.id.skema_pemanfaatan)
        val tujuan = findViewById<TextView>(R.id.tujuan)
        val noSurat = findViewById<TextView>(R.id.no_surat)
        val tglSurat = findViewById<TextView>(R.id.tgl_surat)
        val objek = findViewById<TextView>(R.id.objek)
        val nilai = findViewById<TextView>(R.id.nilai)
        val tglMulai = findViewById<TextView>(R.id.tgl_mulai)
        val tglAkhir = findViewById<TextView>(R.id.tgl_akhir)
        val perihal = findViewById<TextView>(R.id.perihal)

        val PengajuanKerjasamaDetail = listOf<PengajuanKerjasamaDetailModel>(
            PengajuanKerjasamaDetailModel(
                kodeLokasi = "0089879000",
                namaLokasi = "Metro Jaya",
                kodeBarang = "0009-0989-0087",
                namaBarang = "Rumah",
                alamat = "JL. Kundur Jaya",
                Luas = "2000m2",
                LuasManfaat = "1500m2"
            )
        )



        data = intent.getParcelableExtra("PengajuanKerjasama")
//        samain sama i.putExtra!


        nomerPengajuan.text = data?.noPengajuan
        nilai.text = data?.nilai
        namaMitra.text = data?.nama_mitra
        skemaPemanfaatan.text = data?.skemaPemanfaatan
        tujuan.text = data?.tujuan
        noSurat.text = data?.noSurat
        tglSurat.text = data?.tglSurat
        tglAkhir.text = data?.tglAkhir
        tglMulai.text = data?.tglAkhir
        objek.text = data?.objek
        perihal.text = data?.perihal

        setList()
        getPengajuanDetail()
    }

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = PengajuanKerjasamaDetailAdapter(
            this,
            pengajuanKerjasamaDetail,
            object : PengajuanKerjasamaDetailAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    TODO("Not yet implemented")
                }

            }) {

        }

    }

    fun getPengajuanDetail() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "5", object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    pengajuanKerjasamaDetail.clear()
                    data.rows?.forEach {
                        pengajuanKerjasamaDetail?.add(
                            PengajuanKerjasamaDetailModel(
                                kodeLokasi = "0089879000",
                                namaLokasi = "Metro Jaya",
                                kodeBarang = "0009-0989-0087",
                                namaBarang = "Rumah",
                                alamat = "JL. Kundur Jaya",
                                Luas = "2000m2",
                                LuasManfaat = "1500m2"
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