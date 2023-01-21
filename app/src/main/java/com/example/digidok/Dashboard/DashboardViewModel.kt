package com.example.digidok.Dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.dashboardList
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.ProfileModel
import com.example.digidok.data.model.dashboardModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe
import io.reactivex.internal.operators.observable.ObservableFromArray
import java.text.DecimalFormat

class DashboardViewModel(context: Application): AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val username = MutableLiveData<String>()
    val jml = MutableLiveData<String>()
    val jmlMitra = MutableLiveData<String>()
    val jmlNilai = MutableLiveData<String>()

    val mData : MutableList<DashboardModel> = mutableListOf()

    fun getProfileData() {
        mRepository.getProfile(token = token.value.safe(),
            object : DataSource.ProfileCallback {
                override fun onSuccess(data: BaseApiModel<ProfileModel?>) {

                    if (data.isSuccess) {
                        username.value = data.data?.nama
                    } else {
                        mMessageResponse.value = data.message
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

    fun getDashboard() {
        isLoading.value = true
        mRepository.getDashboard(
            token = token.value.safe(),
            object : DataSource.dashboardCallback {
                override fun onSuccess(data: BaseApiModel<dashboardModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mData.clear()
                        var formatter : DecimalFormat = DecimalFormat("#,###")
                        jmlMitra.value = data.data?.jumlahMitra.safe()
                        jmlNilai.value = "Rp. " + formatter.format(data.data?.jumlahNilaiKerjasama)
                        jml.value = data.data?.jumlahKerjasama.safe()
                        data.data?.dataMitra?.forEach {
                            mData?.add(
                                DashboardModel(
                                    nama_mitra = it?.namaMitra.safe(),
                                    jumlah_kerjasama = it?.jumlahKederjasama.safe(),
                                    total_nilai = it?.totalNilai.toString().safe(),
                                    jenis_mitra = it?.idMitra.safe()
                                )
                            )
                        }
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