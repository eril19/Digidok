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

class LaporanPengajuanActivity : AppCompatActivity() {
    var isLoading : Boolean = false
    var laporanPengajuan: ArrayList<LaporanPengajuanModel> = ArrayList()
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
        setContentView(R.layout.activity_laporan_pengajuan)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listTahun)

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
        spinnerWilayah = findViewById<Spinner>(R.id.spinner_wilayah)
        spinnerKelurahan = findViewById<Spinner>(R.id.spinner_kelurahan)
        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        val header = findViewById<TextView>(R.id.header_title)


        header.setText("Laporan Pengajuan Kerjasama")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@LaporanPengajuanActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        //val bg:TextView = findViewById(R.id.header_color)



//        val laporanPengajuanList = listOf<LaporanPengajuanModel>(
//            LaporanPengajuanModel(
//                header_color = "Dikirim",
//                id_mitra = "MT-2020-0001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                no_mitra = "700481948005000",
//                id_pks = "PKS-2022-000001",
//                jenis_kerjasama = "SEWA",
//                no_surat = "21 TAHUN 2017",
//                jenis_bmd = "tanah",
//                nilai_pks = "Rp. 17,687,975,000",
//                detail_pks = "Pemanfaatan Barang Milik Daerah Pemerintah Provinsi DKI Jakarta Berupa Sebagian Tanah yang terletak di Jalan Perintis Kemerdekaan, Kawasan Terminal Kampung Rambutan dan Kawasan Terminal Rawa Buaya untuk Depo TransJakarta",
//                periode = "Periode",
//                jangka_periode ="02/03/2020 - 01/03/2025"
//            ),
//            LaporanPengajuanModel(
//                header_color = "Draft",
//                id_mitra = "MT-2020-0001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                no_mitra = "02.623.519.2-061.000",
//                id_pks = "PKS-2022-000001",
//                jenis_kerjasama = "SEWA",
//                no_surat = "21 TAHUN 2017",
//                jenis_bmd = "tanah",
//                nilai_pks = "Rp. 17,687,975,000",
//                detail_pks = "Pemanfaatan Barang Milik Daerah Pemerintah Provinsi DKI Jakarta Berupa Sebagian Tanah yang terletak di Jalan Perintis Kemerdekaan, Kawasan Terminal Kampung Rambutan dan Kawasan Terminal Rawa Buaya untuk Depo TransJakarta",
//                periode = "Periode",
//                jangka_periode ="02/03/2020 - 01/03/2025"
//            ),
//            LaporanPengajuanModel(
//                header_color = "Dikembalikan",
//                id_mitra = "MT-2020-0001",
//                nama_mitra = "TRANSPORTASI JAKARTA",
//                no_mitra = "01.323.604.7-076.000",
//                id_pks = "PKS-2022-000001",
//                jenis_kerjasama = "SEWA",
//                no_surat = "21 TAHUN 2017",
//                jenis_bmd = "tanah",
//                nilai_pks = "Rp. 17,687,975,000",
//                detail_pks = "Pemanfaatan Barang Milik Daerah Pemerintah Provinsi DKI Jakarta Berupa Sebagian Tanah yang terletak di Jalan Perintis Kemerdekaan, Kawasan Terminal Kampung Rambutan dan Kawasan Terminal Rawa Buaya untuk Depo TransJakarta",
//                periode = "Periode",
//                jangka_periode ="02/03/2020 - 01/03/2025"
//            )
//        )


        setSpinnerKategori()
        setList()
        getLaporanPengajuan()
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
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_laporan_pengajuan)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = LaporanPengajuanAdapter(this, laporanPengajuan){

        }

    }

    fun getLaporanPengajuan() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "10",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    laporanPengajuan.clear()
                    data.data?.forEach {
                        laporanPengajuan?.add(
                            LaporanPengajuanModel(
                                header_color = "Dikembalikan",
                                id_mitra = "MT-2020-0001",
                                nama_mitra = "TRANSPORTASI JAKARTA",
                                no_mitra = "01.323.604.7-076.000",
                                id_pks = "PKS-2022-000001",
                                jenis_kerjasama = "SEWA",
                                no_surat = "21 TAHUN 2017",
                                jenis_bmd = "tanah",
                                nilai_pks = "Rp. 17,687,975,000",
                                detail_pks = "Pemanfaatan Barang Milik Daerah Pemerintah Provinsi DKI Jakarta Berupa Sebagian Tanah yang terletak di Jalan Perintis Kemerdekaan, Kawasan Terminal Kampung Rambutan dan Kawasan Terminal Rawa Buaya untuk Depo TransJakarta",
                                periode = "Periode",
                                jangka_periode ="02/03/2020 - 01/03/2025"
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
