package com.example.digidok.DaftarMitraDetail2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.LaporanPengajuanKerjasama.LaporanPengajuanModel
import com.example.digidok.SpinnerModel.*
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.data.model.jenisMitramodel
import com.example.digidok.data.model.statusMitramodel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class DaftarMitraDetailViewModel2(context: Application) : AndroidViewModel(context)  {
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

    val mData: MutableList<LaporanPengajuanModel> = mutableListOf()
    val mDataJenisMitra: MutableList<JenisMitraModel> = mutableListOf()
    val mDataStatusMitra: MutableList<StatusMitraModel> = mutableListOf()


    fun InsertData(
        npwp: String,
        nama: String,
        alamat: String,
        kelurahan: String,
        kecamatan: String,
        kotaKabupaten: String,
        provinsi: String,
        klasifikasi: String,
        namaKpp: String,
        kanwil: String,
        nomorTelepon: String,
        nomorFax: String,
        email: String,
        ttl: String,
        tanggalDaftar: String,
        statusPkp: String,
        tanggalPengukuhanPkp: String,
        jenisWajibPajak: String,
        badanHukum: String,
        tahunGabung: String,
        jenisMitra: String,
        statusMitra: String,
        legalWp:Long,
        companyProfile: String
    ) {
        isLoading.value = true
        mRepository.InsertMitra(
            token = token.value.safe(),
            npwp = npwp,
            nama = nama,
            alamat = alamat,
            kelurahan = kelurahan,
            kecamatan = kecamatan,
            kotaKabupaten = kotaKabupaten,
            provinsi = provinsi,
            klasifikasi = klasifikasi,
            namaKpp = namaKpp,
            kanwil = kanwil,
            nomorTelepon = nomorTelepon,
            nomorFax = nomorFax,
            email = email,
            ttl = ttl,
            tanggalDaftar = tanggalDaftar,
            statusPkp = statusPkp,
            tanggalPengukuhanPkp = tanggalPengukuhanPkp,
            jenisWajibPajak = jenisWajibPajak,
            badanHukum = badanHukum,
            tahunGabung = tahunGabung,
            jenisMitra = jenisMitra,
            statusMitra = statusMitra,
            companyProfile = companyProfile,
            legalWp = legalWp,
            object : DataSource.InsertMitraCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        responseSucces.value = true
                        mMessageResponse.value = "Data berhasil di tambah"
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

    fun UpdateData(
        npwp: String,
        nama: String,
        alamat: String,
        kelurahan: String,
        kecamatan: String,
        kotaKabupaten: String,
        provinsi: String,
        klasifikasi: String,
        namaKpp: String,
        kanwil: String,
        nomorTelepon: String,
        nomorFax: String,
        email: String,
        ttl: String,
        tanggalDaftar: String,
        statusPkp: String,
        tanggalPengukuhanPkp: String,
        jenisWajibPajak: String,
        badanHukum: String,
        tahunGabung: String,
        jenisMitra: String,
        statusMitra: String,
        legalWp:Long,
        companyProfile: String,
        id:String
    ) {
        isLoading.value = true
        mRepository.updateMitra(
            token = token.value.safe(),
            npwp = npwp,
            nama = nama,
            alamat = alamat,
            kelurahan = kelurahan,
            kecamatan = kecamatan,
            kotaKabupaten = kotaKabupaten,
            provinsi = provinsi,
            klasifikasi = klasifikasi,
            namaKpp = namaKpp,
            kanwil = kanwil,
            nomorTelepon = nomorTelepon,
            nomorFax = nomorFax,
            email = email,
            ttl = ttl,
            tanggalDaftar = tanggalDaftar,
            statusPkp = statusPkp,
            tanggalPengukuhanPkp = tanggalPengukuhanPkp,
            jenisWajibPajak = jenisWajibPajak,
            badanHukum = badanHukum,
            tahunGabung = tahunGabung,
            jenisMitra = jenisMitra,
            statusMitra = statusMitra,
            companyProfile = companyProfile,
            legalWp = legalWp,
            id = id,
            object : DataSource.updateMitraCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        responseSucces.value = true
                        mMessageResponse.value = "Data berhasil di update"
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

    fun getJenisMitra(){
        isLoading.value = true
        mRepository.getJenisMitra(
            token = token.value.safe(),
            object : DataSource.jenisMitraCallback {
                override fun onSuccess(data: BaseApiModel<jenisMitramodel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataJenisMitra.clear()
                        data.data?.dataJenisMitra?.forEach {
                            responseSuccesJenis.value = true
                            mDataJenisMitra?.add(
                                JenisMitraModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
//                        setList()
//                        setSpinnerjenisMitra()
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

    fun getStatusMitra(){
        isLoading.value = true
        mRepository.getStatusMitra(
            token = token.value.safe(),
            object : DataSource.statusMitraCallback {
                override fun onSuccess(data: BaseApiModel<statusMitramodel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        mDataStatusMitra.clear()
                        data.data?.dataStatusMitra?.forEach {
                            responseSuccesStatus.value = true
                            mDataStatusMitra?.add(
                                StatusMitraModel(
                                    value = it?.value.safe(),
                                    label  =it?.label.safe()
                                )
                            )
                        }
//                        setList()
//                        setSpinnerStatusMitra()
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