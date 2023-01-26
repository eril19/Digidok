package com.example.digidok.DaftarMitra

import android.content.Intent
import android.graphics.Typeface
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
import com.example.digidok.DaftarMitraDetail1.MitraDetailActivity
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.Profile.ProfileActivity
import com.example.digidok.R
import com.example.digidok.utils.Preferences

class DaftarMitraActivity : AppCompatActivity() {

    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"
    var statusFilter = "1"
    var spinnerStatus: Spinner? = null
    val listStatus = arrayListOf("NON AKTIF", "AKTIF", "SEMUA")
    var status = 0

    lateinit var mDaftarMitraViewModel: DaftarMitraViewModel

    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_mitra)

        mDaftarMitraViewModel = ViewModelProvider(this@DaftarMitraActivity).get(DaftarMitraViewModel::class.java)
        mDaftarMitraViewModel.token.value = Preferences.isToken(this@DaftarMitraActivity)
        mDaftarMitraViewModel.row.value = "10"
        mDaftarMitraViewModel.order.value = "asc"
        mDaftarMitraViewModel.start.value = "0"
        mDaftarMitraViewModel.sortColumn.value = "no"

        val adapter = ArrayAdapter(applicationContext, R.layout.dd_text_status, listStatus)

        supportActionBar?.hide()

        val progress = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_mitra)

        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        spinnerStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
//                mDaftarMitraViewModel.getDaftarMitra(2)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    status = position-1
                    mDaftarMitraViewModel.getDaftarMitra(position-1)
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
            val i = Intent(this@DaftarMitraActivity, MitraDetailActivity::class.java)
            i.putExtra("menu", "Tambah")
            startActivity(i)
        }

        val homeBtn : ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, DashboardActivity::class.java))
        }

        val homeBtn2 : ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, DashboardActivity::class.java))
        }

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, NotificationActivity::class.java))
        }

        setList()
        observeViewModel()
        setSpinnerKategori()
        mDaftarMitraViewModel.getDaftarMitra(2)

        mDaftarMitraViewModel.isLoading.observe(this){
            if (it){
                progress.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        mDaftarMitraViewModel.responseSucces.observe(this){
            setList()
        }

    }

    private fun observeViewModel() {
        mDaftarMitraViewModel.mMessageResponse.observe(this){
            Toast.makeText(this@DaftarMitraActivity, it, Toast.LENGTH_LONG).show()
        }

    }

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_mitra)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)


        recyclerview?.adapter =
            DaftarMitraAdapter(this, mDaftarMitraViewModel, object : DaftarMitraAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val i = Intent(
                        this@DaftarMitraActivity,
                        MitraDetailActivity::class.java
                    )
                    i.putExtra("menu", "Edit")

                    startActivity(i)
                }

                override fun onItemClickPopupMenu(
                    position: Int,
                    kodeMitra: String,
                    statusMitra: String,
                    idMitra: String,
                    view: View
                ) {
                    val popupPencet = PopupMenu(this@DaftarMitraActivity, view)
                    popupPencet.inflate(R.menu.daftar_mitra_menu)

                    if ( statusMitra.equals("1")) {
                        popupPencet.menu.findItem(R.id.setAktif).isVisible = false

                    } else {
                        popupPencet.menu.findItem(R.id.setNonAktif).isVisible = false
                    }

                    popupPencet.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menuView -> {
                                val i = Intent(
                                    this@DaftarMitraActivity,
                                    MitraDetailActivity::class.java
                                )
                                i.putExtra("menu", "View")
                                i.putExtra("id",kodeMitra)
                                startActivity(i)
                                true
                            }
                            R.id.menuEdit -> {
                                val i = Intent(
                                    this@DaftarMitraActivity,
                                    MitraDetailActivity::class.java
                                )
                                i.putExtra("menu", "Edit")
                                i.putExtra("id",kodeMitra)
                                startActivity(i)
                                true
                            }
                            R.id.setAktif -> {
//                                holder.statusMitra = "Aktif"
//                                holder.header_color.text = "Aktif"
//                                holder.header_color.background = ContextCompat.getDrawable(holder.header_color.context,
//                                    R.color.green
//                                )
                                mDaftarMitraViewModel.getSetAktifNonAktif(kodeMitra, true)
                                true
                            }
                            R.id.setNonAktif -> {
//                                holder.statusMitra = "Tidak Aktif"
//                                holder.header_color.text = "Tidak Aktif"
//                                holder.header_color.background = ContextCompat.getDrawable(holder.header_color.context,
//                                    R.color.red2
//                                )
                                mDaftarMitraViewModel.getSetAktifNonAktif(kodeMitra, false)
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

//    override fun onResume() {
//        super.onResume()
//        mDaftarMitraViewModel.getDaftarMitra(2)
//    }
}