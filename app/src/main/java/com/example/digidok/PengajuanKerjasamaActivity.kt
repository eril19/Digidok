package com.example.digidok

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import com.example.digidok.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.utils.Injection

class PengajuanKerjasamaActivity : AppCompatActivity() {

    var isLoading: Boolean = false
    var pengajuanKerjasama: ArrayList<PengajuanKerjasamaModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    var spinnerStatus: Spinner? = null
    val listStatus = arrayListOf("SEMUA", "DRAFT", "MENUNGGU VALIDASI", "DISETUJUI")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_pengajuan_kerjasama)

        val adapter = ArrayAdapter(applicationContext, R.layout.dd_text_status, listStatus)

        supportActionBar?.hide()

        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Daftar Pengajuan Kerjasama")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
//            val intent = Intent(this@PengajuanKerjasamaActivity, DashboardActivity::class.java)
//            startActivity(intent)
            onBackPressed()
        }

        val tambahMitraBtn = findViewById<Button>(R.id.tambahMitraBtn)
        tambahMitraBtn.setOnClickListener {
            val i = Intent(this@PengajuanKerjasamaActivity, PengajuanKerjasamaDetailActivity::class.java)
            i.putExtra("status", "Edit")
            startActivity(i)
        }

        //val bg:TextView = findViewById(R.id.header_color)


//        val PengajuanKerjasamaList = listOf<PengajuanKerjasamaModel>(
//            PengajuanKerjasamaModel(
//                header_color = "Draft",
//                id_mitra = "MT-2000-0001",
//                nama_mitra = "PT INDOCATER",
//                jenis_mitra = "Perusahaan Swasta",
//                noPengajuan = "002-0203-12120",
//                skemaPemanfaatan = "BTO",
//                tujuan = "Perubahan alamat aset",
//                noSurat = "112-32323-34342",
//                tglSurat = "11/02/2021",
//                objek = "Tanah",
//                nilai = "Rp. 123,030,340",
//                tglMulai = "11/10/2020",
//                tglAkhir = "12/12/2022",
//                perihal = "perubahan",
//            ),
//            PengajuanKerjasamaModel(
//                header_color = "Dikirim",
//
//                id_mitra = "MT-2011-0001",
//                nama_mitra = "PT Wahana Nusantara",
//                jenis_mitra = "Perusahaan Swasta",
//                noPengajuan = "002-0203-12120",
//
//                skemaPemanfaatan = "BTO",
//                tujuan = "Perubahan alamat aset",
//                noSurat = "112-32323-34342",
//                tglSurat = "11/02/2021",
//                objek = "Tanah",
//                nilai = "Rp. 123,030,340",
//                tglMulai = "11/10/2020",
//                tglAkhir = "12/12/2022",
//                perihal = "perubahan",
//            )
//        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_pengajuan_kerjasama)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        setSpinnerKategori()
        setList()
        getPengajuanKerjasama()
    }

    fun setSpinnerKategori() {
        val arrayString = arrayListOf("Pilih Status")
        arrayString.addAll(listStatus)
        spinnerStatus?.adapter =
            object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayString) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    return if (convertView != null) {
                        if (convertView is TextView) {
                            if (position == 0) convertView.setTextColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.black
                                )
                            )
                            try {
                                convertView.typeface = Typeface.createFromAsset(
                                    convertView.context.resources.assets,
                                    "fonts/cs.ttf"
                                )
                            } catch (e: Exception) {
                                showErrorInflateFont()
                            }
                            convertView
                        } else {
                            convertView
                        }
                    } else {
                        super.getView(position, convertView, parent)
                    }
                }
            }
    }

    private fun showErrorInflateFont() = Log.e("FONTFACE", "error when set font face")

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_pengajuan_kerjasama)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = PengajuanKerjasamaAdapter(
            this,
            pengajuanKerjasama,
            object : PengajuanKerjasamaAdapter.onItemClickListener {

                override fun onItemClick(position: Int) {
                    val i = Intent(
                        this@PengajuanKerjasamaActivity,
                        PengajuanKerjasamaDetailActivity::class.java
                    )
                    i.putExtra("PengajuanKerjasama", pengajuanKerjasama[position])
//                    i.putExtra("hideTelaah", true)
                    startActivity(i)
                }
            }) {

        }
    }

    fun getPengajuanKerjasama() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "10", object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    pengajuanKerjasama.clear()
                    data.rows?.forEach {
                        pengajuanKerjasama?.add(
                            PengajuanKerjasamaModel(
                                header_color = "Dikirim",
                                id_pks = "PKS-2022-00001",
                                nama_mitra = "PT Wahana Nusantara",
                                jenis_mitra = "Perusahaan Swasta",
                                noPengajuan = "002-0203-12120",
                                skemaPemanfaatan = "BTO",
                                tujuan = "Perubahan alamat aset",
                                noSurat = "112-32323-34342",
                                tglSurat = "11/02/2021",
                                objek = "Tanah",
                                nilai = "Rp. 123,030,340",
                                tglMulai = "11/10/2020",
                                tglAkhir = "12/12/2022",
                                perihal = "perubahan",
                            )
                        )

                        pengajuanKerjasama?.add(
                            PengajuanKerjasamaModel(
                                header_color = "Dikembalikan",
                                id_pks = "PKS-2022-00001",
                                nama_mitra = "PT Wahana Nusantara",
                                jenis_mitra = "Perusahaan Swasta",
                                noPengajuan = "002-0203-12120",
                                skemaPemanfaatan = "BTO",
                                tujuan = "Perubahan alamat aset",
                                noSurat = "112-32323-34342",
                                tglSurat = "11/02/2021",
                                objek = "Tanah",
                                nilai = "Rp. 123,030,340",
                                tglMulai = "11/10/2020",
                                tglAkhir = "12/12/2022",
                                perihal = "perubahan",
                            )
                        )
                        pengajuanKerjasama?.add(
                            PengajuanKerjasamaModel(
                                header_color = "Draft",
                                id_pks = "PKS-2022-00001",
                                nama_mitra = "PT Wahana Nusantara",
                                jenis_mitra = "Perusahaan Swasta",
                                noPengajuan = "002-0203-12120",
                                skemaPemanfaatan = "BTO",
                                tujuan = "Perubahan alamat aset",
                                noSurat = "112-32323-34342",
                                tglSurat = "11/02/2021",
                                objek = "Tanah",
                                nilai = "Rp. 123,030,340",
                                tglMulai = "11/10/2020",
                                tglAkhir = "12/12/2022",
                                perihal = "perubahan",
                            )
                        )
                        pengajuanKerjasama?.add(
                            PengajuanKerjasamaModel(
                                header_color = "Disetujui",
                                id_pks = "PKS-2022-00001",
                                nama_mitra = "PT Wahana Nusantara",
                                jenis_mitra = "Perusahaan Swasta",
                                noPengajuan = "002-0203-12120",
                                skemaPemanfaatan = "BTO",
                                tujuan = "Perubahan alamat aset",
                                noSurat = "112-32323-34342",
                                tglSurat = "11/02/2021",
                                objek = "Tanah",
                                nilai = "Rp. 123,030,340",
                                tglMulai = "11/10/2020",
                                tglAkhir = "12/12/2022",
                                perihal = "perubahan",
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
