package com.example.digidok.Login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.digidok.Dashboard.DashboardActivity
import com.example.digidok.R
import com.example.digidok.data.Repository
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
    lateinit var mLoginViewModel: LoginViewModel

//    var sharedPref =  getSharedPreferences("myPref", MODE_PRIVATE)
//    var editor = sharedPref.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginViewModel = ViewModelProvider(this@LoginActivity).get(LoginViewModel::class.java)
        loginbtn = findViewById<CardView>(R.id.loginBtn)
        username = findViewById<EditText>(R.id.editTextusername)
        password = findViewById<EditText>(R.id.editTextTextPassword)
        tvLogin = findViewById<TextView>(R.id.tvLogin)
        progressLogin = findViewById<ProgressBar>(R.id.progressLogin)

        supportActionBar?.hide()

        loginbtn?.setOnClickListener {
            mLoginViewModel.username.value = username?.text.toString()
            mLoginViewModel.password.value = password?.text.toString()
            mLoginViewModel.login()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        mLoginViewModel.responseSucces.observe(this){
            if(it){
                Preferences.saveUser(this@LoginActivity, mLoginViewModel.user.value.safe())
                Preferences.saveRole(this@LoginActivity, mLoginViewModel.role.value.safe())
                Preferences.saveToken(this@LoginActivity, mLoginViewModel.token.value.safe())
                Preferences.saveLogin(this@LoginActivity, true)
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            } else {
                Toast.makeText(this@LoginActivity, mLoginViewModel.mMessageResponse.value.toString(), Toast.LENGTH_LONG).show()
            }
        }

        mLoginViewModel.mMessageResponse.observe(this){
            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_LONG).show()
        }

        mLoginViewModel.progressLogin.observe(this){
            if(it){
                tvLogin?.visibility = View.GONE
                progressLogin?.visibility = View.VISIBLE
            } else {
                tvLogin?.visibility = View.VISIBLE
                progressLogin?.visibility = View.GONE
            }
        }
    }

}