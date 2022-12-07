package com.example.digidok.data

import com.example.digidok.BuildConfig.VERSION_NAME
import com.example.digidok.data.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RemoteDataSource : DataSource {

    private val mApiServiceDev = ApiService.getApiService("https://jamc.jakarta.go.id/api-digidok/")
    private val mApiService = ApiService.getApiService("https://jakaset.jakarta.go.id/stagingaset/")

    override fun login(
        user: String,
        password: String,
        deviceId: String,
        fId: String,
        callback: DataSource.LoginDataCallback
    ) {
        mApiServiceDev.login(
            "android", "Android", "login",
            VERSION_NAME, "0", user, password, "android", fId
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<UserModel?>>() {
                override fun onSuccess(model: BaseApiModel<UserModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }


    override fun getProfile(token: String, callback: DataSource.ProfileCallback) {
//        bearer + token ($token itu bawaan kotlin )
        mApiServiceDev.profile("Bearer $token", "Tes")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<ProfileModel?>>() {
                override fun onSuccess(model: BaseApiModel<ProfileModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getDaftarMitra(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        statusFilter: Int,
        callback: DataSource.daftarMitraCallback
    ) {
        mApiServiceDev.daftarMitra(
            token = "Bearer $token",
            start = start,
            row = row,
            order = order,
            sortColumn = sortColumn,
            statusFilter = statusFilter
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<daftarMitraModel?>>() {
                override fun onSuccess(model: BaseApiModel<daftarMitraModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getKJPP(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        callback: DataSource.KJPPCallback
    ) {
        mApiServiceDev.daftarKJPP(
            token = "Bearer $token",
            start = start,
            row = row,
            order = order,
            sortColumn = sortColumn
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<daftarKJPPModel?>>() {
                override fun onSuccess(model: BaseApiModel<daftarKJPPModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getSetAktifNonAktif(
        token: String,
        kodeMitra: String,
        isAktif: Int,
        callback: DataSource.setAktifNonAktifCallback
    ) {
        mApiServiceDev.setAktifNonAktif(
            token = "Bearer $token",
            kodeMitra = kodeMitra,
            isAktif = isAktif
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<setAktifNonAktifModel?>>() {
                override fun onSuccess(model: BaseApiModel<setAktifNonAktifModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getNPWP(token: String, noNpwp: String, callback: DataSource.NPWPCallback) {
        mApiServiceDev.NPWP(token = "Bearer $token", noNpwp = noNpwp)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<NPWPModel?>>() {
                override fun onSuccess(model: BaseApiModel<NPWPModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getKelurahan(token: String, idKota: String, callback: DataSource.kelurahanCallback) {
        mApiServiceDev.Kelurahan(token = "Bearer $token", idKota = idKota)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<kelurahanModel?>>() {
                override fun onSuccess(model: BaseApiModel<kelurahanModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getKota(token: String,callback: DataSource.kotaCallback) {
        mApiServiceDev.Kota(token = "Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<kotaModel?>>() {
                override fun onSuccess(model: BaseApiModel<kotaModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getTahun(token: String,callback: DataSource.tahunCallback) {
        mApiServiceDev.Tahun(token = "Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<tahunModel?>>() {
                override fun onSuccess(model: BaseApiModel<tahunModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getDashboard(token: String,callback: DataSource.dashboardCallback) {
        mApiServiceDev.Dashboard(token = "Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<dashboardModel?>>() {
                override fun onSuccess(model: BaseApiModel<dashboardModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getDaftarPengajuanKerjasamaDetail(
        token: String,
        id: String,
        callback: DataSource.daftarPengajuanDetailCallback
    ) {
        mApiServiceDev.daftarPengajuanDetail(token = "Bearer $token", id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<daftarPengajuanKerjasamaDetailModel?>>() {
                override fun onSuccess(model: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getDaftarPengajuanKerjasama(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        callback: DataSource.daftarPengajuanCallback
    ) {
        mApiServiceDev.daftarPengajuan(
            token = "Bearer $token",
            start = start,
            row = row,
            order = order,
            sortColumn = sortColumn,
            search = search,
            statusFilter = statusFilter
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<daftarPengajuanKerjasamaModel?>>() {
                override fun onSuccess(model: BaseApiModel<daftarPengajuanKerjasamaModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getLaporanAsetDikerjasamakan(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        tahunFilter: Int,
        kelurahanFilter: String,
        callback: DataSource.laporanAsetDikerjasamakanCallback
    ) {
        mApiServiceDev.laporanAsetDikerjasamakan(
            token = "Bearer $token",
            start = start,
            row = row,
            order = order,
            sortColumn = sortColumn,
            search = search,
            statusFilter = statusFilter,
            tahunFilter = tahunFilter,
            kelurahanFilter = kelurahanFilter
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<laporanAsetDikerjasamakanModel?>>() {
                override fun onSuccess(model: BaseApiModel<laporanAsetDikerjasamakanModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getLaporanKerjasama(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        tahunFilter: Int,
        kelurahanFilter: String,
        callback: DataSource.laporanKerjasamaCallback
    ) {
        mApiServiceDev.laporanKerjasama(
            token = "Bearer $token",
            start = start,
            row = row,
            order = order,
            sortColumn = sortColumn,
            search = search,
            statusFilter = statusFilter,
            tahunFilter = tahunFilter,
            kelurahanFilter = kelurahanFilter
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<laporanKerjasamaModel?>>() {
                override fun onSuccess(model: BaseApiModel<laporanKerjasamaModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getRepositoriDokumen(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        tahunFilter: Int,
        kelurahanFilter: String,
        callback: DataSource.repositoriDokumenCallback
    ) {
        mApiServiceDev.repositoriDokumen(
            token = "Bearer $token",
            start = start,
            row = row,
            order = order,
            sortColumn = sortColumn,
            search = search,
            statusFilter = statusFilter,
            tahunFilter = tahunFilter,
            kelurahanFilter = kelurahanFilter
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<repositoriDokumenModel?>>() {
                override fun onSuccess(model: BaseApiModel<repositoriDokumenModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getBerita(start: String, limit: String, callback: DataSource.BeritaDataCallback) {
        mApiService.berita("android", "android", "sampleToken", start, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiCallback<BaseApiModel<BeritaModel?>>() {
                override fun onSuccess(model: BaseApiModel<BeritaModel?>) {
                    model?.let { callback.onSuccess(it) }
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onError(errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }


    override fun onClearDisposables() {
    }
}