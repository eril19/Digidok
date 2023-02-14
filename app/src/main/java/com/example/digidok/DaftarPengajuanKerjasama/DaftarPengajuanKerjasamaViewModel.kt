package com.example.digidok.DaftarPengajuanKerjasama

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.data.model.daftarPengajuanKerjasamaModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class DaftarPengajuanKerjasamaViewModel(context: Application) : AndroidViewModel(context)  {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()
//    val setDatapagination = MutableLiveData<Boolean>()

    val status = MutableLiveData<String>()
    val isPaginating = MutableLiveData(true)
    val isLastPage = MutableLiveData<Boolean>()
    val mData: MutableList<DaftarPengajuanKerjasamaModel> = mutableListOf()
    val setDatapagination = MutableLiveData<Boolean>()

    fun getPengajuanKerjasama(status:String,isClear:Boolean) {
        if(isClear){
            mData.clear()
            isLoading.value = true
            start.value = "0"
        }
        mRepository.getDaftarPengajuanKerjasama(
            token = token.value.safe(),
            start = start.value.safe().toInt(),
            row = 10,
            order = order.value.safe(),
            sortColumn = sortColumn.value.safe(),
            search = "",
            statusFilter = status,
            object : DataSource.daftarPengajuanCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaModel?>) {
//                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        data.data?.dataDokumen?.forEach {
                            mData?.add(
                                DaftarPengajuanKerjasamaModel(
                                    header_color = it?.statusLabel.safe(),
                                    no_pks = it?.idPks.safe(),
                                    nama_mitra = it?.nama.safe(),
                                    periodeAwal = it?.periodeAwal.safe(),
                                    periodeAkhir = it?.periodeAkhir.safe(),
                                )
                            )
                        }
                        if(!isClear) setDatapagination.value = true
                    }

                    if(isClear) responseSucces.value = data.isSuccess
                    if(!isClear) setDatapagination.value = true

                    isLastPage.value = data.data?.dataDokumen?.size != 10
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

    fun setStatus(idStatus: Int, id:String) {
        isLoading.value = true
        mRepository.SetStatus(
            token = token.value.safe(),
            idStatus = idStatus,
            id = id,
            object : DataSource.setStatusCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        mMessageResponse.value = "Berhasil diubah!"
                        getPengajuanKerjasama(status.value?: "SEMUA",true)
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