package com.example.digidok.DaftarPengajuanKerjasama

import android.content.Intent
import android.graphics.Typeface
import com.example.digidok.databinding.ActivityMainBinding
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
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.utils.Preferences
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
    lateinit var mPengajuanKerjasamaViewModel: PengajuanKerjasamaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_pengajuan_kerjasama)

        val adapter = ArrayAdapter(applicationContext, R.layout.dd_text_status, listStatus)

        supportActionBar?.hide()

        mPengajuanKerjasamaViewModel = ViewModelProvider(this@PengajuanKerjasamaActivity).get(PengajuanKerjasamaViewModel::class.java)
        mPengajuanKerjasamaViewModel.token.value = Preferences.isToken(this@PengajuanKerjasamaActivity)
        mPengajuanKerjasamaViewModel.row.value = "10"
        mPengajuanKerjasamaViewModel.order.value = "asc"
        mPengajuanKerjasamaViewModel.start.value = "0"
        mPengajuanKerjasamaViewModel.sortColumn.value = "no"

        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)

        spinnerStatus?.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    if (position-1 == 0){
                    mPengajuanKerjasamaViewModel.getPengajuanKerjasama("SEMUA")
                    }
                    else if(position-1 == 1){
                        mPengajuanKerjasamaViewModel.getPengajuanKerjasama("DRAFT")
                    }
                    else if(position-1 == 2){
                        mPengajuanKerjasamaViewModel.getPengajuanKerjasama("MENUNGGU VALIDASI")
                    }
                    else if(position-1 == 3){
                        mPengajuanKerjasamaViewModel.getPengajuanKerjasama("DISETUJUI")
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
            mPengajuanKerjasamaViewModel.getPengajuanKerjasama("SEMUA")

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
            mPengajuanKerjasamaViewModel,
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
                    } else if (Preferences.Role(this@PengajuanKerjasamaActivity).equals("Staff",ignoreCase = true)){
                        popupPencet.menu.findItem(R.id.menu_telaah).isVisible = false
                    }
                    else{
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

    override fun onResume() {
        super.onResume()
            mPengajuanKerjasamaViewModel.getPengajuanKerjasama("SEMUA")
    }
}
