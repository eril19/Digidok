package com.example.digidok.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.digidok.DaftarMitra.DaftarMitraViewModel
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.Login.LoginActivity
import com.example.digidok.Notification.NotificationActivity
import com.example.digidok.R
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
    var titleProfile : TextView?= null
    var nama = ""
    var keterangan = ""
    var nip = ""
    var telepon = ""
    var email = ""
    var password = ""
    lateinit var mProfileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
        mProfileViewModel = ViewModelProvider(this@ProfileActivity).get(ProfileViewModel::class.java)
        mProfileViewModel.token.value = Preferences.isToken(this@ProfileActivity)

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
            startActivity(Intent(this@ProfileActivity,DashboardActivity::class.java))
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
                mProfileViewModel.updateProfile(nama, nip, telepon, email, keterangan, password)
//                startActivity(Intent(this@ProfileActivity,ProfileActivity::class.java))
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

        mProfileViewModel.getProfileData()
        observeViewModel()

        mProfileViewModel.responseSucces.observe(this){
        observeViewModel()
//            mDaftarMitraViewModel.getDaftarMitra(status)
        }

    }

    private fun observeViewModel() {
        mProfileViewModel.mMessageResponse.observe(this){
            Toast.makeText(this@ProfileActivity, it, Toast.LENGTH_LONG).show()
        }
        mProfileViewModel.username.observe(this){
            uName?.text = mProfileViewModel.username.value
        }
        mProfileViewModel.title.observe(this){
            titleProfile?.text = mProfileViewModel.title.value
        }
        mProfileViewModel.name.observe(this){
            Nama?.setText(mProfileViewModel.name.value)
        }
        mProfileViewModel.desc.observe(this){
            Keterangan?.setText(mProfileViewModel.desc.value)
        }
        mProfileViewModel.nip.observe(this){
            NIP?.setText(mProfileViewModel.nip.value)
        }
        mProfileViewModel.telepon.observe(this){
            Telepon?.setText(mProfileViewModel.telepon.value)
        }
        mProfileViewModel.email.observe(this){
            Email?.setText(mProfileViewModel.email.value)
        }
    }

}