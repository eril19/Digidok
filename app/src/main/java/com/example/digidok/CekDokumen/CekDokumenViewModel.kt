package com.example.digidok.CekDokumen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.cekDokumenModel
import com.example.digidok.data.model.repositoriDokumenModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class CekDokumenViewModel(context: Application) : AndroidViewModel(context)  {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val mData: MutableList<CekDokumenModel> = mutableListOf()

    fun getCekDokumen(id:String) {
        isLoading.value = true
        mRepository.getCekDokumen(
            token = token.value.safe(),
            id = id,
            object : DataSource.CekDokumenCallback {
                override fun onSuccess(data: BaseApiModel<cekDokumenModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mData.clear()
                        data.data?.dataLampiran?.forEach {
                                responseSucces.value = true
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