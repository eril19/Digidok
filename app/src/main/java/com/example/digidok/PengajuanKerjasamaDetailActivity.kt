package com.example.digidok

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.data.model.NPWPModel
import com.example.digidok.data.model.daftarPengajuanKerjasamaDetailModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences

class PengajuanKerjasamaDetailActivity : AppCompatActivity() {

    var isStatusEdit: String = ""
    var isLoading: Boolean = false
    var pengajuanKerjasamaDetail: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    var idPkscheck = ""
    var no_pengajuan: EditText? = null
    var nama_mitra: EditText? = null
    var ETtujuan: EditText? = null
    var no_surat: EditText? = null
    var tgl_surat: EditText? = null
    var Objek: EditText? = null
    var nilai_: EditText? = null
    var tgl_akhir: EditText? = null
    var tgl_mulai: EditText? = null
    var prihal: EditText? = null
    var skema: EditText? = null
    var url = ""
//    var buttonDokumen : Button ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail)

        supportActionBar?.hide()

        idPkscheck = intent.getStringExtra("idPks") ?: ""

        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        val backArrow = findViewById<ImageButton>(R.id.backArrowBtn)
        val next_detail_btn = findViewById<Button>(R.id.next_detail_btn)
        no_pengajuan = findViewById<EditText>(R.id.no_pengajuan)
        nama_mitra = findViewById<EditText>(R.id.nama_mitra)
        ETtujuan = findViewById<EditText>(R.id.tujuan)
        no_surat = findViewById<EditText>(R.id.no_surat)
        tgl_surat = findViewById<EditText>(R.id.tgl_surat)
        Objek = findViewById<EditText>(R.id.objek)
        tgl_mulai = findViewById<EditText>(R.id.tgl_mulai)
        nilai_ = findViewById<EditText>(R.id.nilai)
        tgl_akhir = findViewById<EditText>(R.id.tgl_akhir)
        prihal = findViewById<EditText>(R.id.perihal)
        skema = findViewById<EditText>(R.id.skema_pemanfaatan)
        val buttonDokumen = findViewById<ImageButton>(R.id.buttonDokumen)

        isStatusEdit = intent.getStringExtra("status") ?: ""

        if (isStatusEdit.equals("Edit", true) || isStatusEdit.equals("Tambah",true)) {
            no_pengajuan?.isEnabled = true
            no_pengajuan?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            nama_mitra?.isEnabled = true
            nama_mitra?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            ETtujuan?.isEnabled = true
            ETtujuan?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            no_surat?.isEnabled = true
            no_surat?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            tgl_akhir?.isEnabled = true
            tgl_akhir?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            tgl_mulai?.isEnabled = true
            tgl_mulai?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            tgl_surat?.isEnabled = true
            tgl_surat?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            prihal?.isEnabled = true
            prihal?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            nilai_?.isEnabled = true
            nilai_?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            Objek?.isEnabled = true
            Objek?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)

            skema?.isEnabled = true
            skema?.background =
                ContextCompat.getDrawable(this, R.drawable.custom_profile_enable)
        } else {
            no_pengajuan?.isEnabled = false
            skema?.isEnabled = false
            nama_mitra?.isEnabled = false
            ETtujuan?.isEnabled = false
            no_surat?.isEnabled = false
            tgl_akhir?.isEnabled = false
            tgl_mulai?.isEnabled = false
            tgl_surat?.isEnabled = false
            prihal?.isEnabled = false
            nilai_?.isEnabled = false
            Objek?.isEnabled = false
        }

        if (!idPkscheck.equals("")){
            getPengajuanDetail(idPkscheck)
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

        backArrow.setOnClickListener {
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
                DataAsetdikerjasamakanActivity::class.java
            )
            i.putExtra("hideTelaah", true)
            i.putExtra("idPks",idPkscheck)
            startActivity(i)
        }

        buttonDokumen.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        setList()

    }

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_daftar_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DataAsetdiKerjasamakanAdapter(
            this,
            pengajuanKerjasamaDetail,
            object : DataAsetdiKerjasamakanAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    TODO("Not yet implemented")
                }

            }) {

        }

    }

    fun getPengajuanDetail(idPks: String) {
        com.example.digidok.isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDaftarPengajuanKerjasamaDetail(
            token = Preferences.isToken(context = this@PengajuanKerjasamaDetailActivity),
            id = idPks,
            object : DataSource.daftarPengajuanDetailCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>) {
                    com.example.digidok.isLoading = false
                    if (data.isSuccess) {
                        no_pengajuan?.setText(data.data?.noPengajuan)
                        nama_mitra?.setText(data.data?.mitra)
                        skema?.setText(data.data?.skemaPemanfaatan)
                        ETtujuan?.setText(data.data?.tujuan)
                        no_surat?.setText(data.data?.nomorSurat)
                        tgl_surat?.setText(data.data?.tanggalSurat)
                        Objek?.setText(data.data?.objek)
                        nilai_?.setText("Rp." + data.data?.nilai.toString())
                        tgl_mulai?.setText(data.data?.tanggalMulai)
                        tgl_akhir?.setText(data.data?.tanggalAkhir)
                        prihal?.setText(data.data?.perihal)
                        url = data.data?.dokumen.toString()
                    }
                }

                override fun onError(message: String) {
                    Toast.makeText(this@PengajuanKerjasamaDetailActivity, "Data gagal dimuat", Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
//                    Toast.makeText(this@PengajuanKerjasamaDetailActivity, "Data selesai dimuat", Toast.LENGTH_LONG).show()
                }

            })
    }

}