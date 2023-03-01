package com.example.digidok.DaftarPengajuanKerjasamaDetail2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.DaftarPengajuanKerjasamaDetail1.PengajuanKerjasamaDetailModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.daftarPengajuanKerjasamaDetailModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class DaftarPengajuanKerjasamaDetail2ViewModel(context: Application) : AndroidViewModel(context)  {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    var kolok = MutableLiveData<String>()
    var nalok = MutableLiveData<String>()
    var nabar = MutableLiveData<String>()
    var kobar = MutableLiveData<String>()
    var noreg = MutableLiveData<String>()
    var keterangan = MutableLiveData<String>()
    var luas = MutableLiveData<String>()
    var satuan = MutableLiveData<String>()
    var luasManfaat = MutableLiveData<String>()

    val mData: MutableList<PengajuanKerjasamaDetailModel> = mutableListOf()

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
                        mData.clear()
                        data.data?.dataAsetDikerjasamakan?.forEach {
                            var url = ""
                            mData?.add(
                                PengajuanKerjasamaDetailModel(
                                    noreg = it?.noreg.safe(),
                                    namaLokasi = it?.nalok.safe(),
                                    namaBarang = it?.nabar.safe(),
                                    kodeBarang = it?.kobar.safe(),
                                    kodeLokasi = it?.kolok.safe(),
                                    alamat = it?.keterangan.safe(),
                                    luas = it?.luas.safe(),
                                    luasManfaat = it?.luasManfaat.safe(),
                                    satuan = it?.satuan.safe(),
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