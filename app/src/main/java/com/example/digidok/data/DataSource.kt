package com.example.digidok.data

import com.example.digidok.data.model.*

interface DataSource : BaseDataSource {

    fun login(user: String, password: String, deviceId: String, fid: String, callback: LoginDataCallback)

    fun getBerita(start: String, limit: String, callback: BeritaDataCallback)

    fun getProfile(token:String, callback: ProfileCallback)

    fun getDaftarMitra(token: String, start:Int,row:Int,order:String,sortColumn:String,statusFilter:Int, callback: daftarMitraCallback)

    fun getSetAktifNonAktif(token: String,kodeMitra:String,isAktif:Int, callback: setAktifNonAktifCallback)

    fun getNPWP(token: String, noNpwp:String, callback: NPWPCallback)

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

    interface ProfileCallback {
        fun onSuccess(data: BaseApiModel<ProfileModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface daftarMitraCallback {
        fun onSuccess(data: BaseApiModel<daftarMitraModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface setAktifNonAktifCallback {
        fun onSuccess(data: BaseApiModel<setAktifNonAktifModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface NPWPCallback {
        fun onSuccess(data: BaseApiModel<NPWPModel?>)

        fun onError(message: String)

        fun onFinish()
    }
}