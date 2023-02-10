package com.example.digidok.DaftarKJPP

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.daftarKJPPModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class DaftarKjppViewModel(context: Application) : AndroidViewModel(context) {

    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val isPaginating = MutableLiveData(true)
    val isLastPage = MutableLiveData<Boolean>()

    val mData: MutableList<DaftarKjppModel> = mutableListOf()


    fun getKJPP(isClear: Boolean) {
        if (isClear) {
            isLoading.value = true
        }
        mRepository.getKJPP(
            token = token.value.safe(),
            start = start.value.safe().toInt(),
            row = 10,
            order = order.value.safe(),
            sortColumn = sortColumn.value.safe(),
            object : DataSource.KJPPCallback {
                override fun onSuccess(data: BaseApiModel<daftarKJPPModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        if (isClear) {
                            mData.clear()
                        }
                        data.data?.dataKjpp?.forEach {
                            mData?.add(
                                DaftarKjppModel(
                                    no_kjpp = it?.noInduk.safe(),
                                    no_perizinan = it?.noIzin.safe(),
                                    nama_kjpp = it?.nama.safe(),
                                    tgl_perizinan = it?.tglIzin.safe(),
                                    alamat = it?.alamat.safe(),
                                    telp_kjpp = it?.phone.safe(),
                                    klasifikasi_perizinan = it?.klasifikasiIzin.safe(),

                                    )
                            )
                        }
                    }
                    isLastPage.value = data.data?.dataKjpp?.size != 10
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