package com.example.digidok.Profile

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.Dashboard.DashboardModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.ProfileModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences

class ProfileViewModel(context: Application): AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val username = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val desc = MutableLiveData<String>()
    val nip = MutableLiveData<String>()
    val telepon = MutableLiveData<String>()
    val email = MutableLiveData<String>()
//    val jmlNilai = MutableLiveData<String>()

    val mData : MutableList<ProfileModel> = mutableListOf()

    fun getProfileData() {
        mRepository.getProfile(token = token.value.toString(),
            object : DataSource.ProfileCallback {
                override fun onSuccess(data: BaseApiModel<ProfileModel?>) {
                        responseSucces.value = data.isSuccess
                        isLoading.value = true
                    if (data.isSuccess) {
                        responseSucces.value= data.isSuccess
                        username.value = data.data?.userId
                        title.value = data.data?.nama
                         name.value = data.data?.nama
                        desc.value = data.data?.description
                        nip.value = data.data?.nip
                        telepon.value = data.data?.noHp
                        email.value = data.data?.email
                    }

                }

                override fun onError(message: String) {
                    isLoading.value = false
                    mMessageResponse.value = message
                }

                override fun onFinish() {
                    isLoading.value = false
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
        isLoading.value = true
        mRepository.updateProfile(
            token = token.value.toString(),
            nama = nama,
            nip = nip,
            telepon = telepon,
            email = email,
            keterangan = keterangan,
            password = password,
            object : DataSource.updateProfileCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        mMessageResponse.value = "Ubah data pada profil berhasil"
                        getProfileData()
                    }
                }

                override fun onError(message: String) {
                    isLoading.value = false
                    mMessageResponse.value = message
                }

                override fun onFinish() {
                    isLoading.value = false
                }

            })

    }
}