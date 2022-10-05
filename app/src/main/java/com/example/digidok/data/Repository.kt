package com.example.digidok.data

open class Repository (private val remoteDataSource: DataSource) : DataSource {

    override fun login(
        user: String,
        password: String,
        deviceId: String,
        fid: String,
        callback: DataSource.LoginDataCallback
    ) {
        remoteDataSource.login(user, password, deviceId, fid, callback)
    }

    override fun onClearDisposables() {
        remoteDataSource.onClearDisposables()
    }

    companion object {

        var mRepository: Repository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: RemoteDataSource): Repository {
            if (mRepository == null) {
                mRepository = Repository(remoteDataSource = remoteDataSource)
            }
            return mRepository!!
        }

    }

}