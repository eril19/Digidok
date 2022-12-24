package com.example.digidok

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
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
import com.example.digidok.data.model.setAktifNonAktifModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe
import java.util.*
import kotlin.collections.ArrayList

class DaftarMitraActivity : AppCompatActivity() {

    var start: Int = 0
    var row: Int = 0
    var sortColumn: String = "no"
    var order: String = "asc"
    var statusFilter = "1"
    var spinnerStatus: Spinner? = null
    val listStatus = arrayListOf("NON AKTIF", "AKTIF", "SEMUA")
    //id semua = 0 ,...., non aktif = 2
//    var statusMitra = findViewById<TextView>(R.id.header_color)


    var isLoading: Boolean = false
    var daftarMitra: ArrayList<DaftarMitraModel> = ArrayList()
    private var recyclerview: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_mitra)

        val adapter = ArrayAdapter(applicationContext, R.layout.dd_text_status, listStatus)

        supportActionBar?.hide()

        spinnerStatus = findViewById<Spinner>(R.id.spinner_status)
        spinnerStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                getDaftarMitra(2)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    getDaftarMitra(position - 1)
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

        val profileBtn : ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, ProfileActivity::class.java))
        }

        val notificationBtn : ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@DaftarMitraActivity, NotificationActivity::class.java))
        }

        setList()
        setSpinnerKategori()
        getDaftarMitra(2)

    }

    fun setList() {
        recyclerview = findViewById<RecyclerView>(R.id.rv_list_mitra)
        recyclerview?.layoutManager = LinearLayoutManager(this)
        recyclerview?.setHasFixedSize(true)


        recyclerview?.adapter =
            DaftarMitraAdapter(this, daftarMitra, object : DaftarMitraAdapter.onItemClickListener {
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
                    NPWP: String,
                    view: View
                ) {
                    val popupPencet = PopupMenu(this@DaftarMitraActivity, view)
                    popupPencet.inflate(R.menu.daftar_mitra_menu)

                    if ( statusMitra.equals("Aktif")) {
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
                                i.putExtra("npwp",NPWP)
                                startActivity(i)
                                true
                            }
                            R.id.menuEdit -> {
                                val i = Intent(
                                    this@DaftarMitraActivity,
                                    MitraDetailActivity::class.java
                                )
                                i.putExtra("menu", "Edit")
                                i.putExtra("npwp",NPWP)
                                startActivity(i)
                                true
                            }
                            R.id.setAktif -> {
//                                holder.statusMitra = "Aktif"
//                                holder.header_color.text = "Aktif"
//                                holder.header_color.background = ContextCompat.getDrawable(holder.header_color.context,
//                                    R.color.green
//                                )
                                getSetAktifNonAktif(kodeMitra, true)
                                true
                            }
                            R.id.setNonAktif -> {
//                                holder.statusMitra = "Tidak Aktif"
//                                holder.header_color.text = "Tidak Aktif"
//                                holder.header_color.background = ContextCompat.getDrawable(holder.header_color.context,
//                                    R.color.red2
//                                )
                                getSetAktifNonAktif(kodeMitra, false)
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

    fun getSetAktifNonAktif(kodeMitra: String, isAktif: Boolean) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getSetAktifNonAktif(
            token = Preferences.isToken(context = this@DaftarMitraActivity),
            kodeMitra = kodeMitra,
            isAktif = if (isAktif) {
                1
            } else {
                0
            },
            object : DataSource.setAktifNonAktifCallback {
                override fun onSuccess(data: BaseApiModel<setAktifNonAktifModel?>) {
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

    fun getDaftarMitra(status: Int) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.getDaftarMitra(
            token = Preferences.isToken(context = this@DaftarMitraActivity),
            start = start,
            row = 10,
            order = order,
            sortColumn = sortColumn,
            statusFilter = status,
            object : DataSource.daftarMitraCallback {
                override fun onSuccess(data: BaseApiModel<daftarMitraModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        daftarMitra.clear()
                        data.data?.dataMitra?.forEach {
                            daftarMitra?.add(
                                DaftarMitraModel(
                                    header_color = if (it?.status == 0) {
                                        "Tidak Aktif"
                                    } else if (it?.status == 1) {
                                        "Aktif"
                                    } else {
                                        ""
                                    },
                                    id_mitra = it?.kodeMitra.safe(),
                                    nama_mitra = it?.namaMitra.safe(),
                                    jenis_mitra = it?.jenisMitra.safe(),
                                    status = "Status:",
                                    status_mitra = it?.statusMitra.safe(),
                                    npwp = "NPWP",
                                    npwp_mitra = it?.npwp.safe(),
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


}