package com.example.digidok.data

interface BaseDataSource {

    interface ResponseCallback<T> {
        fun onSuccess(data: T)
        fun onFinish()
        fun onFailed(statusCode: Int, errorMessage: String? = "")
    }

    fun onClearDisposables()
}