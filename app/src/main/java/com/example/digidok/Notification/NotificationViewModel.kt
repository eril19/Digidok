package com.example.digidok.Notification

import android.app.Application
import android.app.Notification
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digidok.DaftarKJPP.DaftarKjppModel
import com.example.digidok.data.DataSource
import com.example.digidok.data.Repository
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.daftarKJPPModel
import com.example.digidok.data.model.notificationModel
import com.example.digidok.utils.Injection
import com.example.digidok.utils.Preferences.safe

class NotificationViewModel(context: Application) : AndroidViewModel(context)  {
    var isLoading = MutableLiveData<Boolean>()
    val token = MutableLiveData<String>()
    val responseSucces = MutableLiveData<Boolean>()
    val mMessageResponse = MutableLiveData<String>()
    val mRepository: Repository = Injection.provideRepository(context)

    val start = MutableLiveData<String>()
    val row = MutableLiveData<String>()
    val sortColumn = MutableLiveData<String>()
    val order = MutableLiveData<String>()

    val mData: MutableList<NotificationModel> = mutableListOf()

    fun getNotification() {
        isLoading.value = true
        mRepository.getNotification(
            token = token.value.safe(),
            object : DataSource.NotificationCallback {
                override fun onSuccess(data: BaseApiModel<notificationModel?>) {
                    responseSucces.value = data.isSuccess
                    isLoading.value = false
                    if (data.isSuccess) {
                        mData.clear()
                        data.data?.dataDokumen?.forEach {
                            mData?.add(
                                NotificationModel(
                                    NotificationTitle = it?.idPks.safe(),
                                    NotificationDetail = it?.namaMitra.safe(),
                                    status = it?.status.safe()
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