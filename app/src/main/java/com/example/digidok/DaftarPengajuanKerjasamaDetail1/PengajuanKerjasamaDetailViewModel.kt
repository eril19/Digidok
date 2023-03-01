package com.example.digidok.DaftarPengajuanKerjasamaDetail1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.SpinnerModel.KategoriPKSModel
import com.example.digidok.SpinnerModel.ListMitraModel
import com.example.digidok.SpinnerModel.TujuanPKSModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.*
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class PengajuanKerjasamaDetailViewModel(context: Application) : AndroidViewModel(context)  {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val responseSuccesTujuan = MutableLiveData<Boolean>()
    val responseSuccesStatus = MutableLiveData<Boolean>()
    val responseSuccesMitra = MutableLiveData<Boolean>()

    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val mData: MutableList<PengajuanKerjasamaDetailModel> = mutableListOf()
    val mDataMitra: MutableList<ListMitraModel> = mutableListOf()
    val mDataKategori: MutableList<KategoriPKSModel> = mutableListOf()
    val mDataTujuan: MutableList<TujuanPKSModel> = mutableListOf()

    var mitra = MutableLiveData<String>()
    var skema = MutableLiveData<String>()
    var tujuan = MutableLiveData<String>()
    var nomorSurat = MutableLiveData<String>()
    var tanggalSurat = MutableLiveData<String>()
    var objek = MutableLiveData<String>()
    var nilai = MutableLiveData<String>()
    var tanggalMulai = MutableLiveData<String>()
    var tanggalAkhir = MutableLiveData<String>()
    var perihal = MutableLiveData<String>()
    var dokumen = MutableLiveData<String>()

    fun getPengajuanKerjasamaDetail(idPks:String) {
        isLoading.value = true
        mRepository.getDaftarPengajuanKerjasamaDetail(
            token = token.value.safe(),
            id = idPks,
            object : DataSource.daftarPengajuanDetailCallback {
                override fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        mitra.value = data.data?.idMitra
                        skema.value = data.data?.idSkemaPemanfaatan
                        tujuan.value = data.data?.idTujuan
                        nomorSurat.value = data.data?.nomorSurat
                        tanggalSurat.value = data.data?.tanggalSurat
                        tanggalMulai.value = data.data?.tanggalMulai
                        tanggalAkhir.value = data.data?.tanggalAkhir
                        perihal.value = data.data?.perihal
                        dokumen.value = data.data?.dokumen
                        nilai.value = data.data?.nilai.toString()
                        objek.value = data.data?.objek
                        getListMitra()
                        getKategoriPKS()
                        getTujuanPKS()

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

    fun getListMitra() {
        isLoading.value = true
        mRepository.getListMitra(
            token = token.value.safe(),
            object : DataSource.listMitraCallback {
                override fun onSuccess(data: BaseApiModel<listMitra?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataMitra.clear()
                        data.data?.dataMitra?.forEach {
                            mDataMitra?.add(
                                ListMitraModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        responseSuccesMitra.value = data.isSuccess
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

    fun getKategoriPKS() {
        isLoading.value = true
        mRepository.getKategoriPKS(
            token = token.value.safe(),
            object : DataSource.kategoriPKSCallback {
                override fun onSuccess(data: BaseApiModel<kategoriPKSmodel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataKategori.clear()
                        data.data?.dataKategoriPks?.forEach {
                            mDataKategori?.add(
                                KategoriPKSModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        responseSuccesStatus.value = data.isSuccess
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

    fun getTujuanPKS() {
        isLoading.value = true
        mRepository.getTujuanPKS(
            token = token.value.safe(),
            object : DataSource.tujuanPKSCallback {
                override fun onSuccess(data: BaseApiModel<tujuanPKSmodel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataTujuan.clear()
                        data.data?.dataTujuanPks?.forEach {
                            mDataTujuan?.add(
                                TujuanPKSModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
                        responseSuccesTujuan.value = true
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

    fun insertPengajuan(
        idMitra: String,
        idKategoriPks: String,
        idTujuanPks: String,
        nomorSurat: String,
        tanggalSurat: String,
        objek: String,
        nilai: String,
        tanggalMulai: String,
        tanggalAkhir: String,
        perihal: String,
        dokumen: String,
    ){
        isLoading.value = true
        mRepository.InsertPengajuan(
            token = token.value.safe(),
            idMitra = idMitra,
            idKategoriPks = idKategoriPks,
            idTujuanPks = idTujuanPks,
            nomorSurat = nomorSurat,
            tanggalSurat = tanggalSurat,
            objek = objek,
            nilai = nilai,
            tanggalMulai = tanggalMulai,
            tanggalAkhir = tanggalAkhir,
            perihal = perihal,
            dokumen = dokumen,
            object : DataSource.InsertPengajuanCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
//                        responseSucces.value = true
                        mMessageResponse.value = "Berhasil menambahkan data"
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

    fun updatePengajuan(
        idMitra: String,
        idKategoriPks: String,
        idTujuanPks: String,
        nomorSurat: String,
        tanggalSurat: String,
        objek: String,
        nilai: String,
        tanggalMulai: String,
        tanggalAkhir: String,
        perihal: String,
        id:String,
        dokumen: String,
    ){
        isLoading.value = true
        mRepository.UpdatePengajuan(
            token = token.value.safe(),
            idMitra = idMitra,
            idKategoriPks = idKategoriPks,
            idTujuanPks = idTujuanPks,
            nomorSurat = nomorSurat,
            tanggalSurat = tanggalSurat,
            objek = objek,
            nilai = nilai,
            tanggalMulai = tanggalMulai,
            tanggalAkhir = tanggalAkhir,
            perihal = perihal,
            id = id,
            dokumen = dokumen,
            object : DataSource.updatePengajuanCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        mMessageResponse.value = "Data berhasil di ubah"
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