package com.example.digidok.DaftarPengajuanKerjasama

import android.content.Intent
import android.graphics.Typeface
import com.example.digidok.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.DaftarPengajuanKerjasamaDetail1.PengajuanKerjasamaDetailActivity
import com.example.digidok.DaftarPengajuanKerjasamaDetail3.DaftarSuratLampiranActivity
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
    val listStatus = arrayListOf("SEMUA", "DRAFT/DIKEMBALIKAN", "DIKIRIM", "DISETUJUI")
    var role = ""
    var status = ""
    lateinit var mDaftarPengajuanKerjasamaViewModel: PengajuanKerjasamaViewModel
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_pengajuan_kerjasama)

        val adapter = ArrayAdapter(applicationContext, R.layout.dd_text_status, listStatus)

        supportActionBar?.hide()

        mDaftarPengajuanKerjasamaViewModel = ViewModelProvider(this@PengajuanKerjasamaActivity).get(PengajuanKerjasamaViewModel::class.java)
        status = "SEMUA"
        mDaftarPengajuanKerjasamaViewModel.token.value = Preferences.isToken(this@PengajuanKerjasamaActivity)
        mDaftarPengajuanKerjasamaViewModel.row.value = "10"
        mDaftarPengajuanKerjasamaViewModel.order.value = "asc"
        mDaftarPengajuanKerjasamaViewModel.start.value = "0"
        mDaftarPengajuanKerjasamaViewModel.sortColumn.value = "no"

        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)

        spinnerStatus?.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    if (position-1 == 0){
                        status = "SEMUA"
                        mDaftarPengajuanKerjasamaViewModel.getPengajuanKerjasama(status,true)
                    }
                    else if(position-1 == 1){
                        status = "DRAFT"
                        mDaftarPengajuanKerjasamaViewModel.getPengajuanKerjasama(status,true)
                    }
                    else if(position-1 == 2){
                        status = "MENUNGGU VALIDASI"
                        mDaftarPengajuanKerjasamaViewModel.getPengajuanKerjasama(status,true)
                    }
                    else if(position-1 == 3){
                        status = "DISETUJUI"
                        mDaftarPengajuanKerjasamaViewModel.getPengajuanKerjasama(status,true)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        role = intent.getStringExtra("role") ?: ""

        setList(role)
        val header = findViewById<TextView>(R.id.header_title)
//        header.setText(role)
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

        val progress = findViewById<ProgressBar>(R.id.progressBar)
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
        observeViewModel()
        mDaftarPengajuanKerjasamaViewModel.getPengajuanKerjasama(status,true)

        mDaftarPengajuanKerjasamaViewModel.isLoading.observe(this){
            if (it){
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        mDaftarPengajuanKerjasamaViewModel.responseSucces.observe(this){
            setList(role)
        }

        mDaftarPengajuanKerjasamaViewModel.setDatapagination.observe(this) {
            (recyclerview?.adapter as PengajuanKerjasamaAdapter).notifyDataSetChanged()
        }
    }

    private fun observeViewModel() {
        mDaftarPengajuanKerjasamaViewModel.setDatapagination.observe(this) {
            (recyclerview?.adapter as PengajuanKerjasamaAdapter).notifyDataSetChanged()
        }
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

    fun setList(role:String) {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_pengajuan_kerjasama)
        mLayoutManager = LinearLayoutManager(this@PengajuanKerjasamaActivity, LinearLayoutManager.VERTICAL, false)
        recyclerview?.layoutManager = mLayoutManager
        recyclerview?.setHasFixedSize(true)

        recyclerview?.adapter = PengajuanKerjasamaAdapter(
            this,
            mDaftarPengajuanKerjasamaViewModel,
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

                    if (statusPengajuan.equals("DISETUJUI",true)) {
                        popupPencet.menu.findItem(R.id.menu_view).isVisible = true
                        popupPencet.menu.findItem(R.id.menu_telaah).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_edit).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_hapus).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_kirim).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_restore).isVisible = false
                    }
                    else if(statusPengajuan.equals("DRAFT",true) || statusPengajuan.equals("DIKEMBALIKAN",true)) {
                        popupPencet.menu.findItem(R.id.menu_view).isVisible = true
                        popupPencet.menu.findItem(R.id.menu_telaah).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_edit).isVisible = true
                        popupPencet.menu.findItem(R.id.menu_hapus).isVisible = true
                        popupPencet.menu.findItem(R.id.menu_kirim).isVisible = true
                        popupPencet.menu.findItem(R.id.menu_restore).isVisible = false
                    }
                    else if(statusPengajuan.equals("DIKIRIM",true)) {
                        popupPencet.menu.findItem(R.id.menu_view).isVisible = true
                        if (role.equals("Supervisor",ignoreCase = true)){
                            popupPencet.menu.findItem(R.id.menu_telaah).isVisible = true
                        }else if (role.equals("Administrator",ignoreCase = true)){
                            popupPencet.menu.findItem(R.id.menu_telaah).isVisible = true
                        }
                        else{
                        popupPencet.menu.findItem(R.id.menu_telaah).isVisible = false
                        }
                        popupPencet.menu.findItem(R.id.menu_edit).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_hapus).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_kirim).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_restore).isVisible = false
                    }
                    else if(statusPengajuan.equals("DIHAPUS",true)) {
                        popupPencet.menu.findItem(R.id.menu_view).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_telaah).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_edit).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_hapus).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_kirim).isVisible = false
                        popupPencet.menu.findItem(R.id.menu_restore).isVisible = true
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
                                mDaftarPengajuanKerjasamaViewModel.setStatus(0, idPks)
                                true
                            }
                            R.id.menu_kirim -> {
                                mDaftarPengajuanKerjasamaViewModel.setStatus(2, idPks)
                                true
                            }
                            R.id.menu_restore -> {
                                mDaftarPengajuanKerjasamaViewModel.setStatus(1, idPks)
                                true
                            }
                        }
                        false
                    }
                    popupPencet.show()
                }
            })

        recyclerview?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = mLayoutManager.childCount
                val totalItemCount = mLayoutManager.itemCount
                val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= 10
                    && mDaftarPengajuanKerjasamaViewModel.isLoading.value == false
                    && mDaftarPengajuanKerjasamaViewModel.isPaginating.value == false
                    && mDaftarPengajuanKerjasamaViewModel.isLastPage.value == false
                ) {
                    mDaftarPengajuanKerjasamaViewModel.isPaginating.value = true
                    Handler().postDelayed({
                        mDaftarPengajuanKerjasamaViewModel.getPengajuanKerjasama(status, false)
                    }, 300)
                }
            }
        })

    }

}
