package com.example.digidok.DaftarMitra

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.DaftarKJPP.DaftarKjppModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.daftarMitraModel
import com.example.digidok.data.model.setAktifNonAktifModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DaftarMitraViewModel (context: Application) : AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)
    val status = MutableLiveData<Int>()
    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val isPaginating = MutableLiveData(true)
    val isLastPage = MutableLiveData<Boolean>()

    val mData: MutableList<DaftarMitraModel> = mutableListOf()

    fun getSetAktifNonAktif(kodeMitra: String, isAktif: Boolean) {
        isLoading.value = true
        mRepository.getSetAktifNonAktif(
            token = token.value.safe(),
            kodeMitra = kodeMitra,
            isAktif = if (isAktif) {
                1
            } else {
                0
            },
            object : DataSource.setAktifNonAktifCallback {
                override fun onSuccess(data: BaseApiModel<setAktifNonAktifModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        mMessageResponse.value =  "Status Mitra Berhasil Diubah"
                        getDaftarMitra(status.value ?: 0, true)
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

    fun getDaftarMitra(status: Int, isClear: Boolean) {
        if(isClear){
            isLoading.value = true
        }
        mRepository.getDaftarMitra(
            token = token.value.safe(),
            start = start.value.safe().toInt(),
            row = 10,
            order = order.value.safe(),
            sortColumn = sortColumn.value.safe(),
            statusFilter = status,
            object : DataSource.daftarMitraCallback {
                override fun onSuccess(data: BaseApiModel<daftarMitraModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        if(isClear){
                            mData.clear()
                        }
                        data.data?.dataMitra?.forEach {
                            mData?.add(
                                DaftarMitraModel(
                                    header_color = it.statusLabel.toUpperCase().safe(),
                                    id_mitra = it?.kodeMitra.safe(),
                                    nama_mitra = it?.namaMitra.safe(),
                                    jenis_mitra = it?.jenisMitra.safe(),
                                    status = "Status:",
                                    statusAktifNonAktf = it?.status.toString(),
                                    status_mitra = it?.statusMitra.safe(),
                                    npwp = "NPWP",
                                    npwp_mitra = it?.npwp.safe(),
                                )
                            )
                        }
                    }

                    isLastPage.value = data.data?.dataMitra?.size != 10
                    start.value = start.value?.toInt()?.plus(10).toString()
                }

                override fun onError(message: String) {
                    isLoading.value = false
                    mMessageResponse.value = message
                }

                override fun onFinish() {
                    isLoading.value = false
                    isPaginating.value = false
                }

            })
    }
}