package com.example.digidok

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.daftarPengajuanKerjasamaDetailModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DaftarSuratLampiranActivity : AppCompatActivity() {
    var idPksCheck : String = ""
    var hideTelaah : String = ""
    var spinnerTelaah : Spinner? = null
    var daftarSuratLampiran: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    val listTelaah = arrayListOf("Disetujui", "Dikembalikan", "Ditolak")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail3)

        supportActionBar?.hide()

        hideTelaah = intent.getStringExtra("status")?:""
        idPksCheck = intent.getStringExtra("idPks") ?: ""

        var menuTelaah = findViewById<LinearLayout>(R.id.menu_telaah)

        if(hideTelaah.equals("Telaah",true)){
            menuTelaah.visibility = View.VISIBLE
        } else {
            menuTelaah.visibility = View.GONE
        }

        if (!idPksCheck.equals("")){
            getPengajuanKerjasamaDetail(idPksCheck)
        }
        
        val close_detail_btn = findViewById<Button>(R.id.close_detail_btn)
        close_detail_btn.setOnClickListener {
            startActivity(Intent(this@DaftarSuratLampiranActivity, PengajuanKerjasamaActivity::class.java))
            finish()
        }

        val prev_detail_btn = findViewById<Button>(R.id.prev_detail_btn)
        prev_detail_btn.setOnClickListener {
//            startActivity(Intent(this@PengajuanKerjasamaDetailActivity3, PengajuanKerjasamaDetailActivity2::class.java))
        onBackPressed()
        }

        spinnerTelaah = findViewById<Spinner>(R.id.spinner_telaah)
        setSpinnerKategori()
    }

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_surat_lampiran)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DaftarSuratLampiranAdapter(
            this,
            daftarSuratLampiran,
            object : DaftarSuratLampiranAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    TODO("Not yet implemented")
                }

            }) {

        }

    }

    fun getPengajuanKerjasamaDetail(idPks:String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDaftarPengajuanKerjasamaDetail(
            token = Preferences.isToken(context = this@DaftarSuratLampiranActivity),
            id = idPks,
            object : DataSource.daftarPengajuanDetailCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        daftarSuratLampiran.clear()
                        data.data?.dataLampiran?.forEach {
                            daftarSuratLampiran?.add(
                                PengajuanKerjasamaDetailModel(
                                    kodeDokumen = it?.kodeDokumen.safe(),
                                    jenisDokumen = it?.jenisDokumen.safe(),
                                    noSurat = it?.noSurat.safe(),
                                    tanggal = it?.tanggal.safe(),
                                    keteranganSurat = it?.keterangan.safe()
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
        val arrayString = arrayListOf("Pilih hasil telaah")
        arrayString.addAll(listTelaah)
        spinnerTelaah?.adapter = object : ArrayAdapter<String>(this, R.layout.dd_text_status, arrayString) {
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