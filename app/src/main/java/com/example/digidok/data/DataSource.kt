package com.example.digidok.data

import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.UserModel

interface DataSource : BaseDataSource {

    fun login(user: String, password: String, deviceId: String, fid: String, callback: LoginDataCallback)

    interface LoginDataCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

}