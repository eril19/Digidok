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

class LaporanAsetActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var daftarLaporanAset: ArrayList<LaporanAsetModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    var spinnerTahun : Spinner? = null
    val listTahun = arrayListOf("2022", "2021", "2020")

    var spinnerWilayah : Spinner? = null
    val listWilayah = arrayListOf("Wilayah 1", "Wilayah 2", "Wilayah 3")

    var spinnerKelurahan : Spinner? = null
    val listKelurahan = arrayListOf("Kelurahan 1", "Kelurahan 2", "Kelurahan 3")

    var spinnerStatus : Spinner? = null
    val listStatus = arrayListOf("Dikirim", "Draft", "Dikembalikan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_laporan_aset)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listTahun)

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
        spinnerWilayah = findViewById<Spinner>(R.id.spinner_wilayah)
        spinnerKelurahan = findViewById<Spinner>(R.id.spinner_kelurahan)
        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        val header = findViewById<TextView>(R.id.header_title)


        header.setText("Laporan Aset Dikerjasamakan")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@LaporanAsetActivity, DashboardActivity::class.java)
            startActivity(intent)
        }

        //val bg:TextView = findViewById(R.id.header_color)



//        val LaporanAset = listOf<LaporanAsetModel>(
//            LaporanAsetModel(
//                header_color = "Dikirim",
//                id_pks = "PKS-2022-000001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                nilai_pks = "Rp. 17,687,975,000",
//                jenis_kerjasama = "SEWA"
//            ),
//            LaporanAsetModel(
//                header_color = "Dikirim",
//                id_pks = "PKS-2022-000001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                nilai_pks = "Rp. 17,687,975,000",
//                jenis_kerjasama = "SEWA"
//            ),
//            LaporanAsetModel(
//                header_color = "Dikembalikan",
//                id_pks = "PKS-2022-000001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                nilai_pks = "Rp. 17,687,975,000",
//                jenis_kerjasama = "SEWA"
//            ),
//            LaporanAsetModel(
//                header_color = "Dikembalikan",
//                id_pks = "PKS-2022-000001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                nilai_pks = "Rp. 17,687,975,000",
//                jenis_kerjasama = "SEWA"
//            ),
//            LaporanAsetModel(
//                header_color = "Draft",
//                id_pks = "PKS-2022-000001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                nilai_pks = "Rp. 17,687,975,000",
//                jenis_kerjasama = "SEWA"
//            ),
//            LaporanAsetModel(
//                header_color = "Draft",
//                id_pks = "PKS-2022-000001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                nilai_pks = "Rp. 17,687,975,000",
//                jenis_kerjasama = "SEWA"
//            ),
//            LaporanAsetModel(
//                header_color = "Dikirim",
//                id_pks = "PKS-2022-000001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                nilai_pks = "Rp. 17,687,975,000",
//                jenis_kerjasama = "SEWA"
//            )
//        )


        setList()
        setSpinnerKategori()
        getDaftarLaporanAset()
    }

    fun setSpinnerKategori() {
        val arrayStringTahun = arrayListOf("Pilih Tahun")
        arrayStringTahun.addAll(listTahun)
        spinnerTahun?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringTahun) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
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

        val arrayStringWilayah = arrayListOf("Pilih Wilayah")
        arrayStringWilayah.addAll(listWilayah)
        spinnerWilayah?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringWilayah) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
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

        val arrayStringKelurahan = arrayListOf("Pilih Kelurahan")
        arrayStringKelurahan.addAll(listKelurahan)
        spinnerKelurahan?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringKelurahan) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
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

        val arrayStringStatus = arrayListOf("Pilih Status")
        arrayStringStatus.addAll(listStatus)
        spinnerStatus?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayStringStatus) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context, R.color.black))
                        try {
                            convertView.typeface = Typeface.createFromAsset(convertView.context.resources.assets, "fonts/cs.ttf")
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

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_laporan_aset)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = LaporanAsetAdapter(this, daftarLaporanAset,object : LaporanAsetAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val i = Intent(this@LaporanAsetActivity, LaporanAsetDetailActivity::class.java)
                i.putExtra("laporanAset", daftarLaporanAset[position])
                startActivity(i)
            }
        }){


        }

    }

    fun getDaftarLaporanAset() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "10",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    daftarLaporanAset.clear()
                    data.rows?.forEach {
                        daftarLaporanAset?.add(
                            LaporanAsetModel(
                                header_color = "Dikirim",
                                id_pks = "PKS-2022-000001",
                                nama_mitra = "TRANSPORTASI JAKARTA",
                                nilai_pks = "Rp. 17,687,975,000",
                                jenis_kerjasama = "SEWA"
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
