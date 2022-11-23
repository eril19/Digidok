package com.example.digidok.data

open class Repository(private val remoteDataSource: DataSource) : DataSource {

    override fun login(
        user: String,
        password: String,
        deviceId: String,
        fid: String,
        callback: DataSource.LoginDataCallback
    ) {
        remoteDataSource.login(user, password, deviceId, fid, callback)
    }

    override fun getBerita(start: String, limit: String, callback: DataSource.BeritaDataCallback) {
        remoteDataSource.getBerita(start, limit, callback)
    }


    override fun getProfile(token: String, callback: DataSource.ProfileCallback) {
        remoteDataSource.getProfile(token, callback)
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
        remoteDataSource.getDaftarMitra(
            token,
            start,
            row,
            order,
            sortColumn,
            statusFilter,
            callback
        )
    }

    override fun getSetAktifNonAktif(
        token: String,
        kodeMitra: String,
        isAktif: Int,
        callback: DataSource.setAktifNonAktifCallback
    ) {
        remoteDataSource.getSetAktifNonAktif(token, kodeMitra, isAktif, callback)
    }

    override fun getNPWP(token: String, noNpwp: String, callback: DataSource.NPWPCallback) {
        remoteDataSource.getNPWP(token, noNpwp, callback)
    }

    override fun getKJPP(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        callback: DataSource.KJPPCallback
    ) {
        remoteDataSource.getKJPP(token, start, row, order, sortColumn, callback)
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
        remoteDataSource.getDaftarPengajuanKerjasama(
            token,
            start,
            row,
            order,
            sortColumn,
            search,
            statusFilter,
            callback
        )
    }


    override fun getDaftarPengajuanKerjasamaDetail(
        token: String,
        id: String,
        callback: DataSource.daftarPengajuanDetailCallback
    ) {
        remoteDataSource.getDaftarPengajuanKerjasamaDetail(token, id, callback)
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
        remoteDataSource.getLaporanAsetDikerjasamakan(
            token,
            start,
            row,
            order,
            sortColumn,
            search,
            statusFilter,
            tahunFilter,
            kelurahanFilter,
            callback
        )
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
        remoteDataSource.getLaporanKerjasama(
            token,
            start,
            row,
            order,
            sortColumn,
            search,
            statusFilter,
            tahunFilter,
            kelurahanFilter,
            callback
        )
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
        remoteDataSource.getRepositoriDokumen(
            token,
            start,
            row,
            order,
            sortColumn,
            search,
            statusFilter,
            tahunFilter,
            kelurahanFilter,
            callback
        )
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