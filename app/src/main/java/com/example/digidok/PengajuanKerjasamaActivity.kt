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
import com.example.digidok.data.model.daftarMitraModel
import com.example.digidok.data.model.daftarPengajuanKerjasamaModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe
import java.util.*
import kotlin.collections.ArrayList

class PengajuanKerjasamaActivity : AppCompatActivity() {

    var isLoading: Boolean = false
    var pengajuanKerjasama: ArrayList<PengajuanKerjasamaModel> = ArrayList()
    private var recyclerview: RecyclerView? = null
    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "idPks"
    var order: String = "asc"
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

        spinnerStatus?.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    if (position-1 == 0){
                    getPengajuanKerjasama("SEMUA")
                    }
                    else if(position-1 == 1){
                        getPengajuanKerjasama("DRAFT")
                    }
                    else if(position-1 == 2){
                        getPengajuanKerjasama("MENUNGGU VALIDASI")
                    }
                    else if(position-1 == 3){
                        getPengajuanKerjasama("DISETUJUI")
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }


        val header = findViewById<TextView>(R.id.header_title)

        header.setText("Daftar Pengajuan Kerjasama")
        val back = findViewById<ImageView>(R.id.backbtn)

        back.setOnClickListener {
            val intent = Intent(this@PengajuanKerjasamaActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        val tambahMitraBtn = findViewById<Button>(R.id.tambahMitraBtn)
        tambahMitraBtn.setOnClickListener {
            val i = Intent(this@PengajuanKerjasamaActivity, PengajuanKerjasamaDetailActivity::class.java)
            i.putExtra("status", "Tambah")
            startActivity(i)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_pengajuan_kerjasama)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@PengajuanKerjasamaActivity, NotificationActivity::class.java))
        }

        setSpinnerKategori()
        setList()
        getPengajuanKerjasama("SEMUA")

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
                    startActivity(i)
                }

                override fun onItemClickPopupMenu(
                    position: Int,
                    statusPengajuan: String,
                    idPks: String,
                    view: View
                ) {
                    val popupPencet = PopupMenu(this@PengajuanKerjasamaActivity, view)
                    popupPencet.inflate(R.menu.daftar_pengajuan_menu)

                    if (statusPengajuan.equals("Dikirim",true)) {
                        popupPencet.menu.findItem(R.id.menu_edit).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_hapus).isVisible = false

                    } else if(statusPengajuan.equals("Disetujui",true)) {
                        popupPencet.menu.findItem(R.id.menu_telaah).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_edit).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_hapus).isVisible = false
                    } else{
                        popupPencet.menu.findItem(R.id.menu_telaah).isVisible = false
                    }

                    popupPencet.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menu_view -> {
                                val intent = Intent(this@PengajuanKerjasamaActivity, PengajuanKerjasamaDetailActivity::class.java)
                                intent.putExtra("status", "View")
                                intent.putExtra("idPks",idPks)
                                startActivity(intent)
                                true
                            }
                            R.id.menu_edit -> {
                                val intent = Intent(this@PengajuanKerjasamaActivity, PengajuanKerjasamaDetailActivity::class.java)
                                intent.putExtra("idPks",idPks)
                                intent.putExtra("status", "Edit")
                                startActivity(intent)
                                true
                            }
                            R.id.menu_telaah -> {
                                val intent = Intent(this@PengajuanKerjasamaActivity, DaftarSuratLampiranActivity::class.java)
                                intent.putExtra("idPks",idPks)
                                intent.putExtra("status", "Telaah")
                                startActivity(intent)
                                true
                            }
                            R.id.menu_hapus -> {
                                val intent = Intent(this@PengajuanKerjasamaActivity, DaftarSuratLampiranActivity::class.java)
                                intent.putExtra("status", "Hapus")
//                                startActivity(intent)
                                true
                            }
                        }
                        false
                    }
                    popupPencet.show()
                }
            }) {

        }
    }

    fun getPengajuanKerjasama(status:String) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDaftarPengajuanKerjasama(
            token = Preferences.isToken(context = this@PengajuanKerjasamaActivity),
            start = start,
            row = 10,
            order = order,
            sortColumn = sortColumn,
            search = "",
            statusFilter = status,
            object : DataSource.daftarPengajuanCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        Toast.makeText(this@PengajuanKerjasamaActivity, "Mohon tunggu sebentar", Toast.LENGTH_SHORT).show()
                        pengajuanKerjasama.clear()
                        data.data?.dataDokumen?.forEach {
                            pengajuanKerjasama?.add(
                                PengajuanKerjasamaModel(
                                    header_color = if (it?.status == 0) {
                                        "DIHAPUS"
                                    } else if (it?.status == 1 || it?.status == -2 ) {
                                        "DRAFT"
                                    }
                                    else if (it?.status == 2) {
                                        "DIKIRIM"
                                    }
                                    else if (it?.status == 3) {
                                        "DISETUJUI"
                                    }
                                    else {
                                        ""
                                    },
                                    no_pks = it?.idPks.safe(),
                                    nama_mitra = it?.nama.safe(),
//                                    jenis_mitra = it?..safe(),
//                                    status = "Status:",
                                    periodeAwal = it?.periodeAwal.safe(),
                                    periodeAkhir = it?.periodeAkhir.safe(),
                                )
                            )
                        }
                        setList()
                    }
            }

            override fun onError(message: String) {
                isLoading = false
                Toast.makeText(this@PengajuanKerjasamaActivity, message, Toast.LENGTH_LONG).show()
            }

            override fun onFinish() {
                isLoading = false
            }

        })
    }

    override fun onResume() {
        super.onResume()
        getPengajuanKerjasama("SEMUA")
    }
}
