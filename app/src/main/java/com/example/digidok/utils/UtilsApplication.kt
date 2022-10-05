package com.example.digidok.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import io.reactivex.plugins.RxJavaPlugins

class UtilsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        APP_CONTEXT = applicationContext

        mInstance = this

        RxJavaPlugins.setErrorHandler { throwable: Throwable? -> }
    }

    companion object {

        var mInstance: UtilsApplication? = null

        @SuppressLint("StaticFieldLeak")
        private var APP_CONTEXT: Context? = null

        @Synchronized
        fun getInstance(): UtilsApplication? {
            return mInstance
        }

        fun getContextInstance(): Context {
            return APP_CONTEXT!!
        }

    }
}