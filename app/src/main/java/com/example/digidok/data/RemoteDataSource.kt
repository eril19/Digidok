package com.example.digidok.data

import com.example.digidok.BuildConfig.VERSION_NAME
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object RemoteDataSource : DataSource {

    private val mApiServiceDev = ApiService.getApiService("https://jakaset.jakarta.go.id/stagingaset/")

    override fun login(user: String, password: String, deviceId: String, fId: String, callback: DataSource.LoginDataCallback) {
        mApiServiceDev.login("android", "Android", "login",
            VERSION_NAME, "0", user, password, "android", fId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<UserModel?>>() {
                override fun onSuccess(model: BaseApiModel<UserModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun onClearDisposables() {
    }
}