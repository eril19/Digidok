package com.example.digidok.RepositoriDokumen

import android.app.Application
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.R
import com.example.digidok.SpinnerModel.KelurahanModel
import com.example.digidok.SpinnerModel.KotaModel
import com.example.digidok.SpinnerModel.TahunModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class RepositoriDokumenViewModel(context: Application) : AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val responseSuccesTahun = MutableLiveData<Boolean>()
    val responseSuccesKelurahan = MutableLiveData<Boolean>()
    val responseSuccesKota = MutableLiveData<Boolean>()
    val spinnerStatusSuccess = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val isPaginating = MutableLiveData(true)
    val isLastPage = MutableLiveData<Boolean>()
    val setDatapagination = MutableLiveData<Boolean>()

    val mData: MutableList<RepositoriDokumenModel> = mutableListOf()
    val mDataKelurahan: MutableList<KelurahanModel> = mutableListOf()
    val mDataKota: MutableList<KotaModel> = mutableListOf()
    val mDataTahun: MutableList<TahunModel> = mutableListOf()

    fun getRepositoriDokumen(statusFitler:String,tahunFilter:String,kelurahanFilter:String,isClear:Boolean) {
        if(isClear){
            mData.clear()
            isLoading.value = true
            start.value = "0"
        }
        mRepository.getRepositoriDokumen(
            token = token.value.safe(),
            start = start.value.safe().toInt(),
            row = 10,
            order = order.value.safe(),
            sortColumn = sortColumn.value.safe(),
            search = "",
            statusFilter = statusFitler,
            tahunFilter = tahunFilter,
            kelurahanFilter = kelurahanFilter,
            object : DataSource.repositoriDokumenCallback {
                override fun onSuccess(data: BaseApiModel<repositoriDokumenModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        data.data?.dataDokumen?.forEach {
                            mData?.add(
                                RepositoriDokumenModel(
                                    header_color = it?.statusLabel.safe(),
                                    nama_mitra = it?.namaMitra.safe(),
                                    jenis_kerjasama = it?.kategoriPks.safe(),
                                    no_surat = it?.idPks.safe(),
                                    harga = it?.nilaiPks.toString().safe(),
                                    idPks = it?.idPks.safe()
                                )
                            )
                        }
                        if(isClear) responseSucces.value = data.isSuccess
                        if(!isClear) setDatapagination.value = true
                    }
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

    fun getTahun() {
        mRepository.getTahun(
            token = token.value.safe(),
            object : DataSource.tahunCallback {
                override fun onSuccess(data: BaseApiModel<tahunModel?>) {
                    if (data.isSuccess) {
                        mDataTahun.clear()
                        data.data?.dataTahun?.forEach {
//                            responseSuccesTahun.value = true
                            mDataTahun?.add(
                                TahunModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        responseSuccesTahun.value = data.isSuccess
                    }
                }

                override fun onError(message: String) {
                    mMessageResponse.value = message
                }

                override fun onFinish() {
                }

            })
    }

    fun getKelurahan(idKota:String) {
        mRepository.getKelurahan(
            token = token.value.safe(),
            idKota = idKota,
            object : DataSource.kelurahanCallback {
                override fun onSuccess(data: BaseApiModel<kelurahanModel?>) {
                    if (data.isSuccess) {
                        mDataKelurahan.clear()
                        data.data?.dataKelurahan?.forEach {
                            mDataKelurahan?.add(
                                KelurahanModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        responseSuccesKelurahan.value = data.isSuccess
                    }
                }

                override fun onError(message: String) {
                    mMessageResponse.value = message
                }

                override fun onFinish() {
                }

            })
    }

    fun getKota() {
        mRepository.getKota(
            token = token.value.safe(),
            object : DataSource.kotaCallback {
                override fun onSuccess(data: BaseApiModel<kotaModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataKota.clear()
                        data.data?.dataKota?.forEach {
//                            responseSuccesKota.value = true
                            mDataKota?.add(
                                KotaModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        responseSuccesKota.value = data.isSuccess
                    }
                }

                override fun onError(message: String) {
                    mMessageResponse.value = message
                }

                override fun onFinish() {

                }

            })
    }

}