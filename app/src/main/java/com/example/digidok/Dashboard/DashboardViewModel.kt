package com.example.digidok.Dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.ProfileModel
import com.example.digidok.data.model.dashboardModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe
import io.reactivex.internal.operators.observable.ObservableFromArray
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class DashboardViewModel(context: Application): AndroidViewModel(context) {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val username = MutableLiveData<String>()
    val jml = MutableLiveData<String>()
    val jmlMitra = MutableLiveData<String>()
    val jmlNilai = MutableLiveData<String>()

    val mData : MutableList<DashboardModel> = mutableListOf()

    fun getProfileData() {
        mRepository.getProfile(token = token.value.safe(),
            object : DataSource.ProfileCallback {
                override fun onSuccess(data: BaseApiModel<ProfileModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = true
                    if (data.isSuccess) {
                        username.value = data.data?.nama
                    } else {
                        mMessageResponse.value = data.message
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

    fun getDashboard() {
        isLoading.value = true
        mRepository.getDashboard(
            token = token.value.safe(),
            object : DataSource.dashboardCallback {
                override fun onSuccess(data: BaseApiModel<dashboardModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        mData.clear()
                        getProfileData()
                        var formatter : DecimalFormat = DecimalFormat("#,###,##")
                        jmlMitra.value = data.data?.jumlahMitra.safe()
                        jmlNilai.value = data.data?.jumlahNilaiKerjasama.safe().toDouble()
                            .convertToCurrency(CurrencyEnum.RUPIAH, true).safe()
                        jml.value = data.data?.jumlahKerjasama.safe()
                        data.data?.dataMitra?.forEach {
                            mData?.add(
                                DashboardModel(
                                    nama_mitra = it?.namaMitra.safe(),
                                    jumlah_kerjasama = it?.jumlahKederjasama.safe(),
                                    total_nilai = it?.totalNilai.toString().safe(),
                                    jenis_mitra = it?.idMitra.safe()
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

    fun Double?.convertToCurrency(currencyType: CurrencyEnum?, showTwoNumberAfterPoint: Boolean = false): String {

        return if (this != null) {
            val kursIndonesia = DecimalFormat.getCurrencyInstance(Locale.ENGLISH) as DecimalFormat
            val formatRp = DecimalFormatSymbols(Locale.ENGLISH)


            val symbol = when (currencyType) {
                null -> {
                    ""
                }
                CurrencyEnum.RUPIAH -> {
                    "Rp. "
                }
                CurrencyEnum.DOLLAR -> {
                    "$ "
                }
            }

            formatRp.currencySymbol = symbol
            formatRp.monetaryDecimalSeparator = ','
            formatRp.groupingSeparator = '.'

            kursIndonesia.decimalFormatSymbols = formatRp
            val result = kursIndonesia.format(this)

            if (!showTwoNumberAfterPoint) {
                result.substring(0, result.length - 3)
            } else {
                result
            }

        } else {
            "Rp. 0"
        }

    }

    enum class CurrencyEnum {
        RUPIAH,
        DOLLAR
    }

}