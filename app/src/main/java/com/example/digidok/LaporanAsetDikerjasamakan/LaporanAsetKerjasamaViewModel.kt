package com.example.digidok.LaporanAsetDikerjasamakan

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.LaporanPengajuanKerjasama.LaporanPengajuanModel
import com.example.digidok.RepositoriDokumen.RepositoriDokumenModel
import com.example.digidok.SpinnerModel.KelurahanModel
import com.example.digidok.SpinnerModel.KotaModel
import com.example.digidok.SpinnerModel.TahunModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class LaporanAsetKerjasamaViewModel(context: Application) : AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val mData: MutableList<LaporanAsetKerjasamaModel> = mutableListOf()
    val mDataDetail: MutableList<LaporanAsetDetailModel> = mutableListOf()
    val mDataKelurahan: MutableList<KelurahanModel> = mutableListOf()
    val mDataKota: MutableList<KotaModel> = mutableListOf()
    val mDataTahun: MutableList<TahunModel> = mutableListOf()

    fun getLaporanAset(statusFitler:String,tahunFilter:Int,kelurahanFilter:String) {
        isLoading.value = true
        mRepository.getLaporanAsetDikerjasamakan(
            token = token.value.safe(),
            start = start.value.safe().toInt(),
            row = 10,
            order = order.value.safe(),
            sortColumn = sortColumn.value.safe(),
            search = "",
            statusFilter = statusFitler,
            tahunFilter = tahunFilter,
            kelurahanFilter = kelurahanFilter,
            object : DataSource.laporanAsetDikerjasamakanCallback {
                override fun onSuccess(data: BaseApiModel<laporanAsetDikerjasamakanModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mData.clear()
                        data.data?.dataDokumen?.forEach {
                            mData?.add(
                                LaporanAsetKerjasamaModel(
                                    header_color = it?.statusLabel.safe(),
                                    id_pks = it?.idPks.safe(),
                                    nama_mitra = it?.namaMitra.safe(),
                                    jenis_kerjasama = it?.kategoriPks.safe(),
                                    nilai_pks = it?.nilaiPks.toString().safe(),
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

    fun getTahun() {
        isLoading.value = true
        mRepository.getTahun(
            token = token.value.safe(),
            object : DataSource.tahunCallback {
                override fun onSuccess(data: BaseApiModel<tahunModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataTahun.clear()
                        data.data?.dataTahun?.forEach {
                            mDataTahun?.add(
                                TahunModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
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

    fun getKelurahan(idKota:String) {
        isLoading.value = true
        mRepository.getKelurahan(
            token = token.value.safe(),
            idKota = idKota,
            object : DataSource.kelurahanCallback {
                override fun onSuccess(data: BaseApiModel<kelurahanModel?>) {
                    isLoading.value = false
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

    fun getKota() {
        isLoading.value = true
        mRepository.getKota(
            token = token.value.safe(),
            object : DataSource.kotaCallback {
                override fun onSuccess(data: BaseApiModel<kotaModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataKota.clear()
                        data.data?.dataKota?.forEach {
                            mDataKota?.add(
                                KotaModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
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
                        mDataDetail.clear()
                        data.data?.dataDokumen?.forEach {
                            it?.dataDetail?.forEach{
                                mDataDetail?.add(
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