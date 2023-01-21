package com.example.digidok.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Login.LoginActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.R
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.ProfileModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.isLoading
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences

class ProfileActivity : AppCompatActivity() {

    val mRepository: Repository = Injection.provideRepository(this)

    var uName: TextView? = null
    var Nama: EditText? = null
    var Keterangan: EditText? = null
    var NIP: EditText? = null
    var Telepon: EditText? = null
    var Email: EditText? = null
    var Password: EditText ? = null
    var titleProfile : TextView?= null
    var nama = ""
    var keterangan = ""
    var nip = ""
    var telepon = ""
    var email = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
        val header = findViewById<TextView>(R.id.header_title)
        val logoout = findViewById<Button>(R.id.logout)
        val back = findViewById<ImageView>(R.id.backbtn)
        val simpan = findViewById<Button>(R.id.simpanBtn)

        uName = findViewById<TextView>(R.id.username)

        Nama = findViewById<EditText>(R.id.name)

        Keterangan = findViewById<EditText>(R.id.keterangan)

        NIP = findViewById<EditText>(R.id.nip)

        Telepon = findViewById<EditText>(R.id.telepon)

        Email = findViewById<EditText>(R.id.email)

        Password = findViewById(R.id.old_password)

        titleProfile = findViewById(R.id.change_profile)

        logoout.setOnClickListener {
            Preferences.saveLogin(this@ProfileActivity, false)
            Preferences.saveUser(this@ProfileActivity, "")
            Preferences.saveRole(this@ProfileActivity, "")
            Preferences.saveToken(this@ProfileActivity, "")
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }

        back.setOnClickListener {
            onBackPressed()
        }

        header.setText("Profile")

        simpan.setOnClickListener {
            email = Email?.text.toString()
            telepon = Telepon?.text.toString()
            nip = NIP?.text.toString()
            keterangan = Keterangan?.text.toString()
            nama = Nama?.text.toString()
            password = Password?.text.toString()

            if(Nama?.text.isNullOrEmpty() || NIP?.text.isNullOrEmpty() || Keterangan?.text.isNullOrEmpty() || Telepon?.text.isNullOrEmpty() || Email?.text.isNullOrEmpty() || Password?.text.isNullOrEmpty()){
                Toast.makeText(this@ProfileActivity, "Tidak boleh ada kolom yang kosong", Toast.LENGTH_SHORT).show()
            }
            else{
                updateProfile(nama, nip, telepon, email, keterangan, password)
            }

        }

        val homeBtn: ImageButton = findViewById(R.id.logo_1)
        homeBtn.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, DashboardActivity::class.java))
        }

        val homeBtn2: ImageButton = findViewById(R.id.logo_2)
        homeBtn2.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, DashboardActivity::class.java))
        }

        val homeBtn3 : ImageButton = findViewById(R.id.homeBtn)
        homeBtn3.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, DashboardActivity::class.java))
        }

        val profileBtn: ImageButton = findViewById(R.id.profileBtn)
        profileBtn.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ProfileActivity::class.java))
        }

        val notificationBtn: ImageButton = findViewById(R.id.notificationBtn)
        notificationBtn.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, NotificationActivity::class.java))
        }

        getProfileData()

    }

    fun getProfileData() {
        mRepository.getProfile(token = Preferences.isToken(this@ProfileActivity),
            object : DataSource.ProfileCallback {
                override fun onSuccess(data: BaseApiModel<ProfileModel?>) {

                    if (data.isSuccess) {
                        uName?.text = data.data?.userId
                        titleProfile?.text = data.data?.nama
                        Nama?.setText(data.data?.nama)
                        Keterangan?.setText(data.data?.description)
                        NIP?.setText(data.data?.nip)
                        Telepon?.setText(data.data?.noHp)
                        Email?.setText(data.data?.email)

                    } else {
//                        messageError = data.message
                        Toast.makeText(this@ProfileActivity, "Data Gagal", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(message: String) {
                    isLoading = false
                    Toast.makeText(this@ProfileActivity, message, Toast.LENGTH_SHORT).show()
                }

                override fun onFinish() {
//                    Toast.makeText(this@ProfileActivity, "Data Selesai", Toast.LENGTH_LONG).show()
                    isLoading = false
                }

            })
    }

    fun updateProfile(
        nama: String,
        nip: String,
        telepon: String,
        email: String,
        keterangan: String,
        password: String
    ) {
        isLoading = true
        val mRepository: Repository = Injection.provideRepository(this)
        mRepository.updateProfile(
            token = Preferences.isToken(context = this@ProfileActivity),
            nama = nama,
            nip = nip,
            telepon = telepon,
            email = email,
            keterangan = keterangan,
            password = password,
            object : DataSource.updateProfileCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading = false
                    if (data.isSuccess) {
                        Toast.makeText(this@ProfileActivity, "Ubah data pada profil berhasil!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(message: String) {
                    Toast.makeText(this@ProfileActivity, "Password salah", Toast.LENGTH_LONG).show()
                    isLoading = false
                }

                override fun onFinish() {
                    isLoading = false
                }

            })

    }
}