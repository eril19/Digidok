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

class CekDokumenActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var cekDokumen: ArrayList<CekDokumenModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var data:RepositoriDokumenModel? = null

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

//        val CekDokumen = listOf<CekDokumenModel>(
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "01 - Dokumen PKS"
//            ),
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "02 - Dokumen SK Gubernur / Sekretaris Daerah"
//            ),
//            CekDokumenModel(
//                header_color = "Dikembalikan",
//                nama_dokumen = "03 - Nota Dinas"
//            ),
//            CekDokumenModel(
//                header_color = "Tidak ada dokumen",
//                nama_dokumen = "04 - Surat Permohonan Mitra"
//            ),
//            CekDokumenModel(
//                header_color = "Dikirim",
//                nama_dokumen = "05 - Surat Penunjukan Kantor Jasa Penilai Publik (KJPP)"
//            ),
//            CekDokumenModel(
//                header_color = "Draft",
//                nama_dokumen = "06 - Surat Hasil Penilaian Kantor Jasa Penilai Publik (KJPP)"
//            ),
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "07 - Notulen Rapat Dengan Mitra"
//            ),
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "08 - Surat Tanda Setoran"
//            ),
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "09 - Dokumen Pajak Bumi dan Bangunan (PBB)"
//            ),
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "10 - Dokumen Asuransi"
//            ),
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "11 - Dokumen Berita Acara Serah Terima (BAST)"
//            ),
//            CekDokumenModel(
//                header_color = "Disetujui",
//                nama_dokumen = "12 - Dokumen Pendukung Lainnya"
//            )
//        )



        tutup.setOnClickListener {
//            val i = Intent(this@CekDokumenActivity, RepositoriDokumenActivity::class.java)
//            startActivity(i)
            onBackPressed()
        }


        setList()
        getCekDokumen()
    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_cek_dokumen)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = CekDokumenAdapter(this,  cekDokumen){

        }
    }

    fun getCekDokumen() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "12",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    cekDokumen.clear()
                    data.rows?.forEach {
                        cekDokumen?.add(
                            CekDokumenModel(
                                header_color = "Disetujui",
                                nama_dokumen = "12 - Dokumen Pendukung Lainnya"
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