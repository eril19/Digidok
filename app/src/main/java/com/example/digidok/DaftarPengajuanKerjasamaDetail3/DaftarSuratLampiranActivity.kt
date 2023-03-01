package com.example.digidok.DaftarPengajuanKerjasamaDetail3

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarPengajuanKerjasama.PengajuanKerjasamaActivity
import com.example.digidok.DaftarPengajuanKerjasamaDetail1.PengajuanKerjasamaDetailModel
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.utils.Preferences

class DaftarSuratLampiranActivity : AppCompatActivity() {
    var idPksCheck : String = ""
    var hideTelaah : String = ""
    var spinnerTelaah : Spinner? = null
    var catatan = ""
    var hasilTelaah = ""
    var catatanPenelaahan : EditText ? = null
    var daftarSuratLampiran: ArrayList<PengajuanKerjasamaDetailModel> = ArrayList()
    var isLoading : Boolean = false
    private var recyclerview: RecyclerView? = null
    val listTelaah = arrayListOf("Disetujui", "Dikembalikan", "Ditolak")
    lateinit var mDaftarPengajuanKerjasamaDetail3ViewModel: DaftarPengajuanKerjasamaDetail3ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_kerjasama_detail3)

        supportActionBar?.hide()
        val header = findViewById<TextView>(R.id.header_title)
        header.setText("Detail Pengajuan Kerjasama")
        var simpan = findViewById<Button>(R.id.simpanBtn)
        var dot = findViewById<ImageView>(R.id.dotTelaah)
        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_surat_lampiran)

        hideTelaah = intent.getStringExtra("status")?:""
        idPksCheck = intent.getStringExtra("idPks") ?: ""

        catatanPenelaahan = findViewById(R.id.catatan_penelaahan)

        var menuTelaah = findViewById<LinearLayout>(R.id.menu_telaah)

        mDaftarPengajuanKerjasamaDetail3ViewModel = ViewModelProvider(this@DaftarSuratLampiranActivity).get(
            DaftarPengajuanKerjasamaDetail3ViewModel::class.java)
        mDaftarPengajuanKerjasamaDetail3ViewModel.token.value = Preferences.isToken(this@DaftarSuratLampiranActivity)

        if(hideTelaah.equals("Telaah",true)){
            menuTelaah.visibility = View.VISIBLE
            dot.visibility = View.GONE
            header.setText("Telaah Pengajuan Kerjasama")
        } else {
            menuTelaah.visibility = View.GONE
        }

        if (!idPksCheck.equals("")){
            mDaftarPengajuanKerjasamaDetail3ViewModel.getPengajuanKerjasamaDetail(idPksCheck)
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

        val backArrow = findViewById<ImageButton>(R.id.backbtn)
        backArrow.setOnClickListener {
            onBackPressed()
        }

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

        observeViewModel()
        setSpinnerKategori()

        val catatanTelaah = findViewById<EditText>(R.id.catatan_penelaahan)

        simpan.setOnClickListener {
            catatan = catatanTelaah.text.toString()
           mDaftarPengajuanKerjasamaDetail3ViewModel.Telaah(hasilTelaah, catatan,idPksCheck)
            startActivity(Intent(this@DaftarSuratLampiranActivity, PengajuanKerjasamaActivity::class.java))
        }

        mDaftarPengajuanKerjasamaDetail3ViewModel.isLoading.observe(this){
            if (it){
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        mDaftarPengajuanKerjasamaDetail3ViewModel.responseSucces.observe(this){
            setList()
        }

    }

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_surat_lampiran)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = DaftarSuratLampiranAdapter(
            this,
            mDaftarPengajuanKerjasamaDetail3ViewModel,
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

    private fun observeViewModel() {
       mDaftarPengajuanKerjasamaDetail3ViewModel.mMessageResponse.observe(this){
            Toast.makeText(this@DaftarSuratLampiranActivity, it, Toast.LENGTH_LONG).show()
        }

    }

    fun setSpinnerKategori() {
        val arrayString = arrayListOf("Pilih hasil telaah")
        arrayString.addAll(listTelaah)
        spinnerTelaah?.adapter = object : ArrayAdapter<String>(this,
            R.layout.dd_text_status, arrayString) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return if (convertView != null) {
                    if (convertView is TextView) {
                        if (position == 0) convertView.setTextColor(ContextCompat.getColor(context,
                            R.color.black
                        ))
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