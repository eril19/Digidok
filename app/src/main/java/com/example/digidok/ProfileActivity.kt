package com.example.digidok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.ProfileModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class ProfileActivity : AppCompatActivity() {

    val mRepository: Repository = Injection.provideRepository(this)

    var uName: TextView? = null
    var Nama: EditText? = null
    var Keterangan: EditText? = null
    var NIP: EditText? = null
    var Telepon: EditText? = null
    var Email: EditText? = null
    var Password: EditText ? = null

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
         nama = Nama?.text.toString()

        Keterangan = findViewById<EditText>(R.id.keterangan)
         keterangan = Keterangan?.text.toString()

        NIP = findViewById<EditText>(R.id.nip)
         nip = NIP?.text.toString()

        Telepon = findViewById<EditText>(R.id.telepon)
         telepon = Telepon?.text.toString()

        Email = findViewById<EditText>(R.id.email)
         email = Email?.text.toString()

        Password = findViewById(R.id.old_password)
         password = Password?.text.toString()

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

            if(Nama?.text.isNullOrEmpty() || NIP?.text.isNullOrEmpty() || Keterangan?.text.isNullOrEmpty() || Telepon?.text.isNullOrEmpty() || Email?.text.isNullOrEmpty() || Password?.text.isNullOrEmpty()){
                Toast.makeText(this@ProfileActivity, "Tidak boleh ada kolom yang kosong", Toast.LENGTH_LONG).show()
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