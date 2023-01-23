package com.example.digidok.DaftarMitra

import android.app.Application
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.DaftarKJPP.DaftarKjppModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.NPWPModel
import com.example.digidok.data.model.detailMitramodel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DaftarMitraDetailViewModel(context: Application) : AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()


    var npwp = MutableLiveData<String>()
    var namaKpp = MutableLiveData<String>()
    var nama = MutableLiveData<String>()
    var alamat = MutableLiveData<String>()
    var kelurahan = MutableLiveData<String>()
    var kecamatan = MutableLiveData<String>()
    var kota = MutableLiveData<String>()
    var provinsi = MutableLiveData<String>()
    var klasifikasi = MutableLiveData<String>()
    var kpp = MutableLiveData<String>()
    var kanwil = MutableLiveData<String>()
    var telp = MutableLiveData<String>()
    var fax = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var ttl = MutableLiveData<String>()
    var tgl_daftar = MutableLiveData<String>()
    var status_pkp = MutableLiveData<String>()
    var tgl_pkp = MutableLiveData<String>()
    var jenis_pajak = MutableLiveData<String>()
    var badan_hukum = MutableLiveData<String>()
    var legalWp = MutableLiveData<Long>()
    var tahunGabung = MutableLiveData<String>()
    var jenisMitra = MutableLiveData<String>()
    var statusMitra = MutableLiveData<String>()
    val mData: MutableList<DaftarMitraDetailModel> = mutableListOf()

    fun getDetailMitra(id:String){
        isLoading.value = true
        mRepository.getDetailMitra(
            token = token.value.safe(),
            id = id,
            object : DataSource.detailMitraCallback {
                override fun onSuccess(data: BaseApiModel<detailMitramodel?>) {
                    com.example.digidok.isLoading = false
                    if (data.isSuccess) {
                        npwp.value = data.data?.npwp
                        nama.value =data.data?.nama
                        alamat.value =data.data?.alamat
                        kelurahan.value =data.data?.kelurahan
                        kecamatan.value =data.data?.kecamatan
                        kota.value =data.data?.kotaKabupaten
                        provinsi.value =data.data?.provinsi
                        klasifikasi.value =data.data?.klasifikasi
                        kpp.value =data.data?.namaKpp
                        kanwil.value =data.data?.kanwil
                        telp.value =data.data?.nomorTelepon
                        fax.value =data.data?.nomorFax
                        email.value =data.data?.email
                        ttl.value =data.data?.ttl
                        tgl_daftar.value =data.data?.tanggalDaftar
                        status_pkp.value =data.data?.statusPkp
                        tgl_pkp.value =data.data?.tanggalPengukuhanPkp
                        jenis_pajak.value =data.data?.jenisWajibPajak
                        badan_hukum.value =data.data?.badanHukum
                        legalWp.value = data.data?.legalWp ?: 0
                        statusMitra.value = data.data?.statusMitra.safe()
                        jenisMitra.value = data.data?.jenisMitra.safe()
                        tahunGabung.value = data.data?.tahunGabung.toString().safe()
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

    fun getNpwp(noNpwp: String) {
        isLoading.value = true
        mRepository.getNPWP(
            token = token.value.safe(),
            noNpwp = noNpwp,
            object : DataSource.NPWPCallback {
                override fun onSuccess(data: BaseApiModel<NPWPModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        npwp.value = noNpwp
                        nama.value =data.data?.nama
                        alamat.value =data.data?.alamat
                        kelurahan.value =data.data?.kelurahan
                        kecamatan.value =data.data?.kecamatan
                        kota.value =data.data?.kabKota
                        provinsi.value =data.data?.provinsi
                        klasifikasi.value =data.data?.klasifikasiKlu
//                        kpp.value =data.data?.k
                        kanwil.value =data.data?.kanwil
                        telp.value =data.data?.nomorTelepon
                        fax.value =data.data?.nomorFax
                        email.value =data.data?.email
                        ttl.value =data.data?.ttl
                        tgl_daftar.value =data.data?.tanggalDaftar
                        status_pkp.value =data.data?.statusPkp
                        tgl_pkp.value =data.data?.tanggalPengukuhanPkp
                        jenis_pajak.value =data.data?.jenisWajibPajak
                        badan_hukum.value =data.data?.badanHukum
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