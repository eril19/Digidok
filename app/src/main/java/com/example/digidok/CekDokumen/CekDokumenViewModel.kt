package com.example.digidok.CekDokumen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.repositoriDokumenModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class CekDokumenViewModel(context: Application) : AndroidViewModel(context)  {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val mData: MutableList<CekDokumenModel> = mutableListOf()

    fun getCekDokumen() {
        isLoading.value = true
        mRepository.getRepositoriDokumen(
            token = token.value.safe(),
            start = start.value.safe().toInt(),
            row = 10,
            order = order.value.safe(),
            sortColumn = sortColumn.value.safe(),
            search = "",
            statusFilter = "SEMUA",
            tahunFilter = 2017,
            kelurahanFilter = "",
            object : DataSource.repositoriDokumenCallback {
                override fun onSuccess(data: BaseApiModel<repositoriDokumenModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mData.clear()
                        data.data?.dataDokumen?.forEach {
                            it?.dataLampiran?.forEach{
                                mData?.add(
                                    CekDokumenModel(
                                        header_color = "Dokumen",
                                        nama_dokumen = it?.label.safe(),
                                        file = it?.file.safe()
                                    )
                                )
                            }
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