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
import java.util.*
import kotlin.collections.ArrayList

class DaftarMitraActivity : AppCompatActivity() {

    var spinnerStatus : Spinner? = null
    val listStatus = arrayListOf("SEMUA", "AKTIF", "NON AKTIF")

    var isLoading : Boolean = false
    var daftarMitra: ArrayList<DaftarMitraModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_daftar_mitra)

        val adapter = ArrayAdapter(applicationContext,R.layout.dd_text_status, listStatus)

        supportActionBar?.hide()

        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        spinnerStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0){
                    getDaftarMitra()
                }
            }

        }

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Daftar Mitra")

        val back = findViewById<ImageView>(R.id.backbtn)
        back.setOnClickListener {
            val intent = Intent(this@DaftarMitraActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        val tambahMitraBtn = findViewById<Button>(R.id.tambahMitraBtn)
        tambahMitraBtn.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, MitraDetailActivity::class.java))
        }


        //val bg:TextView = findViewById(R.id.header_color)



//        val DaftarMitraList = listOf<DaftarMitraModel>(
//            DaftarMitraModel(
//                header_color = "Aktif",
//                id_mitra = "MT-2000-0001",
//                nama_mitra = "PT INDOCATER",
//                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
//                status_mitra = "whitelist",
//                npwp = "NPWP",
//                npwp_mitra = "02.623.519.2-061.000",
//            ),
//            DaftarMitraModel(
//                header_color = "Tidak Aktif",
//                id_mitra = "MT-2011-0001",
//                nama_mitra = "PT Wahana Nusantara",
//                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
//                status_mitra = "whitelist",
//                npwp = "NPWP",
//                npwp_mitra = "013121280073000",
//            ),
//            DaftarMitraModel(
//                header_color = "Tidak Aktif",
//                id_mitra = "MT-2011-0001",
//                nama_mitra = "PT Wahana Nusantara",
//                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
//                status_mitra = "whitelist",
//                npwp = "NPWP",
//                npwp_mitra = "013121280073000",
//            ),
//            DaftarMitraModel(
//                header_color = "Aktif",
//                id_mitra = "MT-2011-0001",
//                nama_mitra = "PT Wahana Nusantara",
//                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
//                status_mitra = "whitelist",
//                npwp = "NPWP",
//                npwp_mitra = "013121280073000",
//            ),
//            DaftarMitraModel(
//                header_color = "Aktif",
//                id_mitra = "MT-2011-0001",
//                nama_mitra = "PT Wahana Nusantara",
//                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
//                status_mitra = "whitelist",
//                npwp = "NPWP",
//                npwp_mitra = "013121280073000",
//            ),
//            DaftarMitraModel(
//                header_color = "Aktif",
//                id_mitra = "MT-2011-0001",
//                nama_mitra = "PT Wahana Nusantara",
//                jenis_mitra = "Perusahaan Swasta",
//                status = "Status:",
//                status_mitra = "whitelist",
//                npwp = "NPWP",
//                npwp_mitra = "013121280073000",
//            )
//        )

        setList()
        setSpinnerKategori()
        getDaftarMitra()

    }

    fun setList(){
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_mitra)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)


        recyclerview?.adapter = DaftarMitraAdapter(this, daftarMitra,object : DaftarMitraAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val i = Intent(this@DaftarMitraActivity, MitraDetailActivity::class.java)
                i.putExtra("daftarMitra", daftarMitra[position])
                i.putExtra("hideButton", false)
                startActivity(i)
            }
        }){


        }
    }

    fun getDaftarMitra() {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getBerita("0", "10",  object : DataSource.BeritaDataCallback {
            override fun onSuccess(data: BaseApiModel<BeritaModel?>) {
                isLoading = false
                if (data.success) {
                    daftarMitra.clear()
                    data.rows?.forEach {
                        daftarMitra?.add(
                            DaftarMitraModel(
                                header_color = "Aktif",
                                id_mitra = "MT-2011-0001",
                                nama_mitra = "PT Wahana Nusantara",
                                jenis_mitra = "Perusahaan Swasta",
                                status = "Status:",
                                status_mitra = "whitelist",
                                npwp = "NPWP",
                                npwp_mitra = "013121280073000",
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


    fun setSpinnerKategori() {
        val arrayString = arrayListOf("Pilih Status")
        arrayString.addAll(listStatus)
        spinnerStatus?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayString) {
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