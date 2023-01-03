package com.example.digidok.data

import com.example.digidok.data.model.BaseApiModel
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApiCallback<M> : Observer<M> {

    abstract fun onSuccess(model: M)

    abstract fun onFailure(code: Int, errorMessage: String)

    abstract fun onFinish()

    override fun onComplete() {
        onFinish()
    }

    override fun onNext(t: M) {
        if (t is BaseApiModel<*> && (t.status == 401 || t.status == 103)) {

        }

        onSuccess(t)
    }


    override fun onSubscribe(d: Disposable) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val code = e.code()
                var msg = e.message()
                var baseDao: BaseApiModel<M>? = null
                try {
                    val body = e.response()?.errorBody()
                    baseDao = Gson().fromJson<BaseApiModel<M>>(body!!.string(), BaseApiModel::class.java)
                } catch (exception: Exception) {
                    when (exception) {
                        is IllegalStateException -> onFailure(-1, "Invalid server response")
                        is java.lang.IllegalThreadStateException -> onFailure(-1, "Oops! There is a problem while processing the data from server.")
                        is UnknownHostException -> onFailure(-1, "Cant connect to host, ${e.message}")
                        is SocketTimeoutException -> onFailure(-1, "Cant connect to host, socket time out, ${e.message}")
                        else -> onFailure(-1, e.message ?: "Unknown error occured")
                    }
                }

                when (code) {
                    504 -> {
                        msg = baseDao?.message ?: "Error Response"
                    }
                    502, 404 -> {
                        msg = baseDao?.message ?: "Error Connect or Resource Not Found"
                    }
                    400 -> {
                        msg = baseDao?.error ?: "Bad Request"
                    }
                    401, 103 -> {
                        msg = baseDao?.message ?: "Bad Request"
                        return
                    }
                }

                onFailure(code, msg)
            }

            is IllegalStateException -> onFailure(-1, "Invalid server response")
            is java.lang.IllegalThreadStateException -> onFailure(-1, "Oops! There is a problem while processing the data from server.")
            is UnknownHostException -> onFailure(-1, "Cant connect to host, ${e.message}")
            is SocketTimeoutException -> onFailure(-1, "Cant connect to host, socket time out, ${e.message}")
            else -> onFailure(-1, e.message ?: "Unknown error occured")
        }

        onFinish()
    }
}