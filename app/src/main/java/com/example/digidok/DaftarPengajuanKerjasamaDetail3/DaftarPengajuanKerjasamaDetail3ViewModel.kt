package com.example.digidok.DaftarPengajuanKerjasamaDetail3

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.DaftarPengajuanKerjasamaDetail1.DaftarPengajuanKerjasamaDetailModel
import com.example.digidok.LaporanPengajuanKerjasama.LaporanPengajuanModel
import com.example.digidok.SpinnerModel.JenisMitraModel
import com.example.digidok.SpinnerModel.StatusMitraModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.data.model.daftarPengajuanKerjasamaDetailModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences
import com.example.digidok.utils.Preferences.safe

class DaftarPengajuanKerjasamaDetail3ViewModel(context: Application) : AndroidViewModel(context)  {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val mData: MutableList<DaftarPengajuanKerjasamaDetailModel> = mutableListOf()

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
                        data.data?.dataLampiran?.forEach {
//                            responseSucces.value = true
                            var url = ""
                            mData?.add(
                                DaftarPengajuanKerjasamaDetailModel(
                                    kodeDokumen = it?.kodeDokumen.safe(),
                                    jenisDokumen = it?.jenisDokumen.safe(),
                                    noSurat = it?.noSurat.safe(),
                                    tanggalDokumen = it?.tanggal.safe(),
                                    keteranganSurat = it?.keterangan.safe(),
                                    lampiranLink = it?.file.safe()
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

    fun Telaah(hasilTelaah:String, catatan : String, id:String){
        isLoading.value = true
        mRepository.Telaah(
            token = token.value.safe(),
            hasilTelaah = hasilTelaah,
            catatan = catatan,
            id = id,
            object : DataSource.TelaahCallback {
                override fun onSuccess(data: BaseApiModel<UserModel?>) {
                    isLoading.value = false
                    if (data.isSuccess) {
                        responseSucces.value = true
                        mMessageResponse.value = "Telaah Berhasil"
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