package com.example.digidok.LaporanAsetDikerjasamakanDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.LaporanPengajuanKerjasama.LaporanPengajuanModel
import com.example.digidok.SpinnerModel.JenisMitraModel
import com.example.digidok.SpinnerModel.StatusMitraModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.laporanAsetDikerjasamakanModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class LaporanAsetDetailViewModel(context: Application) : AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val responseSuccesJenis = MutableLiveData<Boolean>()
    val responseSuccesStatus = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val mData: MutableList<LaporanAsetDetailModel> = mutableListOf()

    fun getLaporanAsetDetail() {
        isLoading.value = true
        mRepository.getLaporanAsetDikerjasamakan(
            token = token.value.safe(),
            start = start.value.safe().toInt(),
            row = 10,
            order = order.value.safe(),
            sortColumn = sortColumn.value.safe(),
            search = "",
            statusFilter = "SEMUA",
            tahunFilter = 2017,
            kelurahanFilter = "",
            object : DataSource.laporanAsetDikerjasamakanCallback {
                override fun onSuccess(data: BaseApiModel<laporanAsetDikerjasamakanModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mData.clear()
                        data.data?.dataDokumen?.forEach {
                            it?.dataDetail?.forEach{
                                mData?.add(
                                    LaporanAsetDetailModel(
                                        kode_barang = it?.kobar.safe(),
                                        nama_bmd = it?.nabar.safe(),
                                        kode_lokasi = it?.kolok.safe(),
                                        nama_lokasi = it?.nalok.safe(),
                                        luas_bmd = it?.luas.safe() + " "+ it?.satuan.safe(),
                                        keterangan_bmd = it?.keterangan.safe(),
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