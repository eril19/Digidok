package com.example.digidok

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe


class LoginActivity : AppCompatActivity() {

    val mRepository: Repository = Injection.provideRepository(this)

    private var loginbtn: CardView? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var tvLogin: TextView? = null
    private var progressLogin: ProgressBar? = null
    var messageError = ""

//    var sharedPref =  getSharedPreferences("myPref", MODE_PRIVATE)
//    var editor = sharedPref.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        loginbtn = findViewById<CardView>(R.id.loginBtn)
        username = findViewById<EditText>(R.id.editTextusername)
        password = findViewById<EditText>(R.id.editTextTextPassword)
        tvLogin = findViewById<TextView>(R.id.tvLogin)
        progressLogin = findViewById<ProgressBar>(R.id.progressLogin)

        supportActionBar?.hide()

        loginbtn?.setOnClickListener {
            login()
        }
    }

    fun login() {
        statusLogin(true)
        mRepository.login(username?.text.toString(), password?.text.toString(), "androidTes", "fIdTes",
            object : DataSource.LoginDataCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    statusLogin(false)
                    if (data.isSuccess) {
                        val userName = findViewById<EditText>(R.id.editTextusername).text.toString()
                        Preferences.saveUser(this@LoginActivity, data.data?.namaUser.safe())
                        Preferences.saveRole(this@LoginActivity,data.data?.namaRole.safe())
                        Preferences.saveToken(this@LoginActivity, data.data?.token.safe())
                        Preferences.saveLogin(this@LoginActivity, true)
                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                    } else {
                        messageError = data.message
                        Toast.makeText(this@LoginActivity, messageError, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(message: String) {
                    statusLogin(false)
                }

                override fun onFinish() {
                    statusLogin(false)
                }

            })
    }

    fun statusLogin(status: Boolean){
        if (status){
            tvLogin?.visibility = View.GONE
            progressLogin?.visibility = View.VISIBLE
        } else {
            tvLogin?.visibility = View.VISIBLE
            progressLogin?.visibility = View.GONE
        }
    }
}