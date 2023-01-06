package com.example.digidok

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
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
import com.example.digidok.data.model.UserModel
import com.example.digidok.data.model.daftarPengajuanKerjasamaDetailModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DaftarSuratLampiranActivity : AppCompatActivity() {
    var idPksCheck : String = ""
    var hideTelaah : String = ""
    var spinnerTelaah : Spinner? = null
    var catatan = ""
    var hasilTelaah = ""
    var catatanPenelaahan : EditText ? = null
    var daftarSuratLampiran: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    val listTelaah = arrayListOf("Disetujui", "Dikembalikan", "Ditolak")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail3)

        supportActionBar?.hide()
        var simpan = findViewById<Button>(R.id.simpanBtn)

        hideTelaah = intent.getStringExtra("status")?:""
        idPksCheck = intent.getStringExtra("idPks") ?: ""

        catatanPenelaahan = findViewById(R.id.catatan_penelaahan)

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
        onBackPressed()
        }

        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Detail Pengajuan Kerjasama")

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@DaftarSuratLampiranActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@DaftarSuratLampiranActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@DaftarSuratLampiranActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DaftarSuratLampiranActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DaftarSuratLampiranActivity, NotificationActivity::class.java))
        }

        spinnerTelaah = findViewById<Spinner>(R.id.spinner_telaah)
        spinnerTelaah?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position-1 == 0){
                    hasilTelaah = "3"
                }
                else if (position-1 == 1){
                    hasilTelaah = "-2"
                }
                else if (position-1 == 2){
                    hasilTelaah = "-1"
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        setSpinnerKategori()

        val catatanTelaah = findViewById<EditText>(R.id.catatan_penelaahan)

        simpan.setOnClickListener {
            catatan = catatanTelaah.text.toString()
            Telaah(hasilTelaah, catatan)
            startActivity(Intent(this@DaftarSuratLampiranActivity, PengajuanKerjasamaActivity::class.java))
        }

    }

    fun Telaah(hasilTelaah:String, catatan : String){

        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.Telaah(
            token = Preferences.isToken(context = this@DaftarSuratLampiranActivity),
            hasilTelaah = hasilTelaah,
            catatan = catatan,
            object : DataSource.TelaahCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading = false
                    if (data.isSuccess) {

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

                override fun onItemClickCekLampiran(position: Int, link: String) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.setDataAndType(Uri.parse(link), "application/pdf");
                        startActivity(intent)
                    }catch (e : Exception){
                        Toast.makeText(this@DaftarSuratLampiranActivity, "Tidak ada aplikasi untuk menampilkan file PDF", Toast.LENGTH_SHORT).show()
                    }
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
                            var url = ""
                            daftarSuratLampiran?.add(
                                PengajuanKerjasamaDetailModel(
                                    kodeDokumen = it?.kodeDokumen.safe(),
                                    jenisDokumen = it?.jenisDokumen.safe(),
                                    noSurat = it?.noSurat.safe(),
                                    tanggalDokumen = it?.tanggal.safe(),
                                    keteranganSurat = it?.keterangan.safe(),
                                    lampiranLink = it?.file.safe()
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