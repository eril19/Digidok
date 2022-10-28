package com.example.digidok.data

import com.example.digidok.BuildConfig.VERSION_NAME
import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.data.model.UserModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RemoteDataSource : DataSource {

    private val mApiServiceDev = ApiService.getApiService("https://jamc.jakarta.go.id/api-digidok/")
    private val mApiService = ApiService.getApiService("https://jakaset.jakarta.go.id/stagingaset/")

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

    override fun getBerita(start: String, limit: String, callback: DataSource.BeritaDataCallback) {
        mApiService.berita("android", "android", "sampleToken", start, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<BeritaModel?>>() {
                override fun onSuccess(model: BaseApiModel<BeritaModel?>) {
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