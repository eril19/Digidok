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

    var uName : TextView? = null
    var Nama :EditText? = null
    var Keterangan : EditText?=null
    var NIP : EditText?=null
    var Telepon : EditText?=null
    var Email : EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.hide()
        val header = findViewById<TextView>(R.id.header_title)
        val logoout = findViewById<Button>(R.id.logout)
        val back = findViewById<ImageView>(R.id.backbtn)


        uName =  findViewById<TextView>(R.id.username)
        Nama = findViewById<EditText>(R.id.name)
        Keterangan = findViewById<EditText>(R.id.keterangan)
         NIP = findViewById<EditText>(R.id.nip)
         Telepon = findViewById<EditText>(R.id.telepon)
         Email = findViewById<EditText>(R.id.email)

        logoout.setOnClickListener {
            Preferences.saveLogin(this@ProfileActivity, false)
            Preferences.saveUser(this@ProfileActivity, "")
            Preferences.saveRole(this@ProfileActivity,"")
            Preferences.saveToken(this@ProfileActivity, "")
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
        }

        back.setOnClickListener {
//            val intent = Intent(this@ProfileActivity, DashboardActivity::class.java)
//            startActivity(intent)
            onBackPressed()
        }
        header.setText("Profile")

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
                         NIP?.setText( data.data?.nip)
                         Telepon?.setText(data.data?.noHp)
                         Email?.setText(data.data?.email)

                    } else {
//                        messageError = data.message
                        Toast.makeText(this@ProfileActivity, "Data Gagal", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(message: String) {

                }

                override fun onFinish() {
                    Toast.makeText(this@ProfileActivity, "Data Selesai", Toast.LENGTH_LONG).show()
                }

            })
    }
}