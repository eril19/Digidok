package com.example.digidok.data

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.digidok.data.model.*
import com.example.digidok.utils.UtilsApplication
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
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
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("deviceId") deviceId: String,
        @Field("fid") fid: String
    ): Observable<BaseApiModel<UserModel?>>

    @FormUrlEncoded
    @POST("auth/get-data")
    fun profile(
        @Header("Authorization") token: String,
        @Field("fid") fid: String
    ): Observable<BaseApiModel<ProfileModel?>>

    @FormUrlEncoded
    @POST("setup/mitra/set-aktif")
    fun setAktifNonAktif(
        @Header("Authorization") token: String,
        @Field("kodeMitra") kodeMitra: String,
        @Field("isAktif") isAktif:Int,
    ): Observable<BaseApiModel<setAktifNonAktifModel?>>

    @GET("setup/mitra/get-data")
    fun daftarMitra(
        @Header("Authorization") token: String,
        @Query("start") start: Int,
        @Query("row") row: Int,
        @Query("order") order: String,
        @Query("sortColumn") sortColumn: String,
        @Query("statusFilter") statusFilter: Int,

        ): Observable<BaseApiModel<daftarMitraModel?>>

    @GET("formulir/dokumen/get-data")
    fun daftarPengajuan(
        @Header("Authorization") token: String,
        @Query("start") start: Int,
        @Query("row") row: Int,
        @Query("order") order: String,
        @Query("sortColumn") sortColumn: String,
        @Query("search") search: String,
        @Query("statusFilter") statusFilter: String,

        ): Observable<BaseApiModel<daftarPengajuanKerjasamaModel?>>

    @GET("pelaporan/laporan-kerjasama/get-data")
    fun laporanKerjasama(
        @Header("Authorization") token: String,
        @Query("start") start: Int,
        @Query("row") row: Int,
        @Query("order") order: String,
        @Query("sortColumn") sortColumn: String,
        @Query("search") search: String,
        @Query("statusFilter") statusFilter: String,
        @Query("tahunFilter") tahunFilter: Int,
        @Query("kelurahanFilter") kelurahanFilter: String,
        ): Observable<BaseApiModel<laporanKerjasamaModel?>>

    @GET("pelaporan/laporan-aset-dikerjasamakan/get-data")
    fun laporanAsetDikerjasamakan(
        @Header("Authorization") token: String,
        @Query("start") start: Int,
        @Query("row") row: Int,
        @Query("order") order: String,
        @Query("sortColumn") sortColumn: String,
        @Query("search") search: String,
        @Query("statusFilter") statusFilter: String,
        @Query("tahunFilter") tahunFilter: Int,
        @Query("kelurahanFilter") kelurahanFilter: String,
    ): Observable<BaseApiModel<laporanAsetDikerjasamakanModel?>>

    @GET("pelaporan/repositori-dokumen/get-data")
    fun repositoriDokumen(
        @Header("Authorization") token: String,
        @Query("start") start: Int,
        @Query("row") row: Int,
        @Query("order") order: String,
        @Query("sortColumn") sortColumn: String,
        @Query("search") search: String,
        @Query("statusFilter") statusFilter: String,
        @Query("tahunFilter") tahunFilter: Int,
        @Query("kelurahanFilter") kelurahanFilter: String,
    ): Observable<BaseApiModel<repositoriDokumenModel?>>

    @GET("formulir/dokumen/get-detail/")
    fun daftarPengajuanDetail(
        @Header("Authorization") token: String,
        @Query("id") id: String,
        ): Observable<BaseApiModel<daftarPengajuanKerjasamaDetailModel?>>

    @GET("setup/kjpp/get-data")
    fun daftarKJPP(
        @Header("Authorization") token: String,
        @Query("start") start: Int,
        @Query("row") row: Int,
        @Query("order") order: String,
        @Query("sortColumn") sortColumn: String,
        ): Observable<BaseApiModel<daftarKJPPModel?>>

    @GET("master/get-npwp")
    fun NPWP(
        @Header("Authorization") token: String,
        @Query("noNpwp") noNpwp: String,
        ): Observable<BaseApiModel<NPWPModel?>>

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
                .addInterceptor(ChuckerInterceptor(UtilsApplication.getContextInstance()))
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

