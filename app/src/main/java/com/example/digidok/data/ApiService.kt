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
    @POST("profil/ubah")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Field("nama") nama: String,
        @Field("nip") nip: String,
        @Field("telepon") telepon: String,
        @Field("email") email: String,
        @Field("keterangan") keterangan: String,
        @Field("password") password: String,
    ): Observable<BaseApiModel<UserModel?>>

    @FormUrlEncoded
    @POST("setup/mitra/submit/tambah")
    fun insertMitra(
        @Header("Authorization") token: String,
        @Field("npwp") npwp: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("kelurahan") kelurahan: String,
        @Field("kecamatan") kecamatan: String,
        @Field("kotaKabupaten") kotaKabupaten: String,
        @Field("provinsi") provinsi: String,
        @Field("klasifikasi") klasifikasi: String,
        @Field("namaKpp") namaKpp: String,
        @Field("kanwil") kanwil: String,
        @Field("nomorTelepon") nomorTelepon: String,
        @Field("nomorFax") nomorFax: String,
        @Field("email") email: String,
        @Field("ttl") ttl: String,
        @Field("tanggalDaftar") tanggalDaftar: String,
        @Field("statusPkp") statusPkp: String,
        @Field("tanggalPengukuhanPkp") tanggalPengukuhanPkp: String,
        @Field("jenisWajibPajak") jenisWajibPajak: String,
        @Field("badanHukum") badanHukum: String,
        @Field("tahunGabung") tahunGabung: String,
        @Field("jenisMitra") jenisMitra: String,
        @Field("statusMitra") statusMitra: String,
        @Field("companyProfile") companyProfile: String,
        @Field("legalWp") legalWp: Long,
        ): Observable<BaseApiModel<UserModel?>>

    @FormUrlEncoded
    @POST("setup/mitra/submit/ubah")
    fun updateMitra(
        @Header("Authorization") token: String,
        @Field("npwp") npwp: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("kelurahan") kelurahan: String,
        @Field("kecamatan") kecamatan: String,
        @Field("kotaKabupaten") kotaKabupaten: String,
        @Field("provinsi") provinsi: String,
        @Field("klasifikasi") klasifikasi: String,
        @Field("namaKpp") namaKpp: String,
        @Field("kanwil") kanwil: String,
        @Field("nomorTelepon") nomorTelepon: String,
        @Field("nomorFax") nomorFax: String,
        @Field("email") email: String,
        @Field("ttl") ttl: String,
        @Field("tanggalDaftar") tanggalDaftar: String,
        @Field("statusPkp") statusPkp: String,
        @Field("tanggalPengukuhanPkp") tanggalPengukuhanPkp: String,
        @Field("jenisWajibPajak") jenisWajibPajak: String,
        @Field("badanHukum") badanHukum: String,
        @Field("tahunGabung") tahunGabung: String,
        @Field("jenisMitra") jenisMitra: String,
        @Field("statusMitra") statusMitra: String,
        @Field("companyProfile") companyProfile: String,
        @Field("legalWp") legalWp: Long,
        @Field("id") id: String,
    ): Observable<BaseApiModel<UserModel?>>

    @FormUrlEncoded
    @POST("formulir/dokumen/submit/tambah")
    fun insertPengajuan(
        @Header("Authorization") token: String,
        @Field("idMitra") idMitra: String,
        @Field("idKategoriPks") idKategoriPks: String,
        @Field("idTujuanPks") idTujuanPks: String,
        @Field("nomorSurat") nomorSurat: String,
        @Field("tanggalSurat") tanggalSurat: String,
        @Field("objek") objek: String,
        @Field("nilai") nilai: String,
        @Field("tanggalMulai") tanggalMulai: String,
        @Field("tanggalAkhir") tanggalAkhir: String,
        @Field("perihal") perihal: String,
        @Field("dokumen") dokumen: String,
    ): Observable<BaseApiModel<UserModel?>>

    @FormUrlEncoded
    @POST("formulir/dokumen/submit/ubah")
    fun updatePengajuan(
        @Header("Authorization") token: String,
        @Field("idMitra") idMitra: String,
        @Field("idKategoriPks") idKategoriPks: String,
        @Field("idTujuanPks") idTujuanPks: String,
        @Field("nomorSurat") nomorSurat: String,
        @Field("tanggalSurat") tanggalSurat: String,
        @Field("objek") objek: String,
        @Field("nilai") nilai: String,
        @Field("tanggalMulai") tanggalMulai: String,
        @Field("tanggalAkhir") tanggalAkhir: String,
        @Field("perihal") perihal: String,
        @Field("id") id: String,
        @Field("dokumen") dokumen: String,
    ): Observable<BaseApiModel<UserModel?>>

    @FormUrlEncoded
    @POST("formulir/dokumen/telaah")
    fun Telaah(
        @Header("Authorization") token: String,
        @Field("hasilTelaah") hasilTelaah: String,
        @Field("catatan") catatan: String,
        @Field("id") id: String,
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

    @FormUrlEncoded
    @POST("formulir/dokumen/set-status")
    fun setStatus(
        @Header("Authorization") token: String,
        @Field("idStatus") idStatus: Int,
        @Field("id") id:String,
    ): Observable<BaseApiModel<UserModel?>>

    @GET("formulir/dokumen/get-notifikasi")
    fun notification(
        @Header("Authorization") token: String,
        ): Observable<BaseApiModel<notificationModel?>>

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

    @GET("pelaporan/laporan-aset-dikerjasamakan/detail")
    fun laporanAsetDetail(
        @Header("Authorization") token: String,
        @Query("id") id: String,
    ): Observable<BaseApiModel<laporanAsetDetailModel?>>

    @GET("pelaporan/repositori-dokumen/detail")
    fun cekDokumen(
        @Header("Authorization") token: String,
        @Query("id") id: String,
    ): Observable<BaseApiModel<cekDokumenModel?>>

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

    @GET("setup/mitra/detail")
    fun MitraDetail(
        @Header("Authorization") token: String,
        @Query("id") id: String,
    ): Observable<BaseApiModel<detailMitramodel?>>

    @GET("master/get-tahun-dokumen")
    fun Tahun(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<tahunModel?>>


    @GET("master/get-kota")
    fun Kota(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<kotaModel?>>


    @GET("master/get-kategori-pks")
    fun KategoriPKS(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<kategoriPKSmodel?>>


    @GET("master/get-tujuan-pks")
    fun TujuanPKS(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<tujuanPKSmodel?>>


    @GET("master/get-status-mitra")
    fun StatusMitra(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<statusMitramodel?>>


    @GET("master/get-mitra")
    fun ListMitra(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<listMitra?>>

    @GET("master/get-jenis-mitra")
    fun JenisMitra(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<jenisMitramodel?>>


    @GET("dashboard")
    fun Dashboard(
        @Header("Authorization") token: String,
    ): Observable<BaseApiModel<dashboardModel?>>


    @GET("master/get-kelurahan")
    fun Kelurahan(
        @Header("Authorization") token: String,
        @Query("idKota") idKota: String,
    ): Observable<BaseApiModel<kelurahanModel?>>

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

