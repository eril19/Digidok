package com.example.digidok.data

import com.example.digidok.data.model.BaseApiModel
import com.example.digidok.data.model.BeritaModel
import com.example.digidok.data.model.UserModel
import com.example.digidok.utils.UtilsApplication
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Header("deviceType") deviceType: String,
        @Header("deviceName") deviceName: String,
        @Header("url") url: String,
        @Header("versionName") versinName: String,
        @Header("versionCode") versinCode: String,
        @Field("user") user: String,
        @Field("password") password: String,
        @Field("deviceId") deviceId: String,
        @Field("fid") fid: String
    ): Observable<BaseApiModel<UserModel?>>

    @FormUrlEncoded
    @POST("cms/berita")
    fun berita(
        @Header("deviceType") deviceType: String,
        @Header("deviceName") deviceName: String,
        @Header("Authorization") token: String,
        @Field("start") start: String,
        @Field("limit") limit: String
    ): Observable<BaseApiModel<BeritaModel?>>

    companion object Factory {

        fun getApiService(baseUrl: String): ApiService {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val mClient = OkHttpClient.Builder()
                .addInterceptor(mLoggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                    val url = request.url.newBuilder().build()
                    val newRequest = request.newBuilder().url(url).build()
                    chain.proceed(newRequest)
                }
                .addInterceptor(ChuckInterceptor(UtilsApplication.getContextInstance()))
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build()

            return mRetrofit.create(ApiService::class.java)
        }
    }

}

