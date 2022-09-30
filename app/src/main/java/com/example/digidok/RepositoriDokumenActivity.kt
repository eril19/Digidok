package com.example.digidok

import android.content.Intent
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

class RepositoriDokumenActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_repositori_dokumen)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listTahun)

        supportActionBar?.hide()

        spinnerTahun = findViewById<Spinner>(R.id.spinner_tahun)
        spinnerWilayah = findViewById<Spinner>(R.id.spinner_wilayah)
        spinnerKelurahan = findViewById<Spinner>(R.id.spinner_kelurahan)
        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        val header = findViewById<TextView>(R.id.header_title)


        header.setText("Repositori Dokumen")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@RepositoriDokumenActivity, MenuActivity::class.java)
            startActivity(intent)
        }

        //val bg:TextView = findViewById(R.id.header_color)



        val RepositoriList = listOf<RepositoriDokumenModel>(
            RepositoriDokumenModel(
                header_color = "Dikirim",
                jenis_kerjasama = "SEWA",
                harga = "Rp. 4.000.000",
                no_surat = "21 Tahun 2017",
                nama_mitra = "TRANSPORTASI JAKARTA"
            ),
            RepositoriDokumenModel(
                header_color = "Dikirim",
                jenis_kerjasama = "SEWA",
                harga = "Rp. 4.000.000",
                no_surat = "15 Tahun 2015",
                nama_mitra = "KACEDIRA EXPRESS"
            ),
            RepositoriDokumenModel(
                header_color = "Dikembalikan",
                jenis_kerjasama = "SEWA",
                harga = "Rp. 4.000.000",
                no_surat = "12 Tahun 2012",
                nama_mitra = "TRANSPORTASI BIBIR SEXY"
            ),
            RepositoriDokumenModel(
                header_color = "Draft",
                jenis_kerjasama = "SEWA",
                harga = "Rp. 4.000.000",
                no_surat = "22 Tahun 2024",
                nama_mitra = "TRANSPORTASI BICEP PURNOMO"
            ),
            RepositoriDokumenModel(
                header_color = "Dikembalikan",
                jenis_kerjasama = "SEWA",
                harga = "Rp. 1.000.000",
                no_surat = "10 Tahun 2021",
                nama_mitra = "NAMA MITRA 3"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_repositori)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = RepositoriDokumemAdapter(this,  RepositoriList, object:RepositoriDokumemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val i = Intent(this@RepositoriDokumenActivity, CekDokumenActivity::class.java)
                i.putExtra("Cek Dokumen",RepositoriList[position])
                startActivity(i)
            }

        }
        ){

        }

        setSpinnerKategori()

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


}
