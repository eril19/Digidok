package com.example.digidok.data

import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.data.model.UserModel

interface DataSource : BaseDataSource {

    fun login(user: String, password: String, deviceId: String, fid: String, callback: LoginDataCallback)

    fun getBerita(start: String, limit: String, callback: BeritaDataCallback)

    interface LoginDataCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface BeritaDataCallback {
        fun onSuccess(data: BaseApiModel<BeritaModel?>)

        fun onError(message: String)

        fun onFinish()
    }


}