package com.example.digidok.Login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class LoginViewModel(context: Application): AndroidViewModel(context) {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val user = MutableLiveData<String>()
    val role = MutableLiveData<String>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val progressLogin = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val repository: Repository = Injection.provideRepository(context)

    fun login() {
        progressLogin.value = true
        repository.login(username?.value.safe(), password?.value.safe(), "androidTes", "fIdTes",
            object : DataSource.LoginDataCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    progressLogin.value = false
                    user.value = data.data?.namaUser.safe()
                    role.value = data.data?.namaRole.safe()
                    token.value = data.data?.token.safe()
                    responseSucces.value = data.isSuccess
                    mMessageResponse.value = data.message
                }

                override fun onError(message: String) {
                    progressLogin.value = false
                    mMessageResponse.value = message
                    responseSucces.value = false
                }

                override fun onFinish() {
                    progressLogin.value = false
                }

            })
    }

}