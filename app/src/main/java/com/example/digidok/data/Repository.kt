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

    override fun updateProfile(
        token: String,
        nama: String,
        nip: String,
        telepon: String,
        email: String,
        keterangan: String,
        password: String,
        callback: DataSource.updateProfileCallback
    ) {
        remoteDataSource.updateProfile(token,nama, nip, telepon, email, keterangan, password, callback)
    }

    override fun InsertPengajuan(
        token: String,
        idMitra: String,
        idKategoriPks: String,
        idTujuanPks: String,
        nomorSurat: String,
        tanggalSurat: String,
        objek: String,
        nilai: String,
        tanggalMulai: String,
        tanggalAkhir: String,
        perihal: String,
        dokumen: String,
        callback: DataSource.InsertPengajuanCallback
    ) {
        remoteDataSource.InsertPengajuan(token, idMitra, idKategoriPks, idTujuanPks, nomorSurat, tanggalSurat, objek, nilai, tanggalMulai, tanggalAkhir, perihal, dokumen, callback)
    }

    override fun UpdatePengajuan(
        token: String,
        idMitra: String,
        idKategoriPks: String,
        idTujuanPks: String,
        nomorSurat: String,
        tanggalSurat: String,
        objek: String,
        nilai: String,
        tanggalMulai: String,
        tanggalAkhir: String,
        perihal: String,
        id: String,
        dokumen: String,
        callback: DataSource.updatePengajuanCallback
    ) {
        remoteDataSource.UpdatePengajuan(token, idMitra, idKategoriPks, idTujuanPks, nomorSurat, tanggalSurat, objek, nilai, tanggalMulai, tanggalAkhir, perihal, id, dokumen, callback)
    }

    override fun InsertMitra(
        token: String,
        npwp: String,
        nama: String,
        alamat: String,
        kelurahan: String,
        kecamatan: String,
        kotaKabupaten: String,
        provinsi: String,
        klasifikasi: String,
        namaKpp: String,
        kanwil: String,
        nomorTelepon: String,
        nomorFax: String,
        email: String,
        ttl: String,
        tanggalDaftar: String,
        statusPkp: String,
        tanggalPengukuhanPkp: String,
        jenisWajibPajak: String,
        badanHukum: String,
        tahunGabung: String,
        jenisMitra: String,
        statusMitra: String,
        companyProfile: String,
        legalWp:Long,
        callback: DataSource.InsertMitraCallback
    ) {
        remoteDataSource.InsertMitra(token, npwp, nama, alamat, kelurahan, kecamatan, kotaKabupaten, provinsi, klasifikasi, namaKpp, kanwil, nomorTelepon, nomorFax, email, ttl, tanggalDaftar, statusPkp, tanggalPengukuhanPkp, jenisWajibPajak, badanHukum, tahunGabung, jenisMitra, statusMitra, companyProfile, legalWp,callback)
    }

    override fun updateMitra(
        token: String,
        npwp: String,
        nama: String,
        alamat: String,
        kelurahan: String,
        kecamatan: String,
        kotaKabupaten: String,
        provinsi: String,
        klasifikasi: String,
        namaKpp: String,
        kanwil: String,
        nomorTelepon: String,
        nomorFax: String,
        email: String,
        ttl: String,
        tanggalDaftar: String,
        statusPkp: String,
        tanggalPengukuhanPkp: String,
        jenisWajibPajak: String,
        badanHukum: String,
        tahunGabung: String,
        jenisMitra: String,
        statusMitra: String,
        companyProfile: String,
        legalWp: Long,
        id: String,
        callback: DataSource.updateMitraCallback
    ) {
        remoteDataSource.updateMitra(token, npwp, nama, alamat, kelurahan, kecamatan, kotaKabupaten, provinsi, klasifikasi, namaKpp, kanwil, nomorTelepon, nomorFax, email, ttl, tanggalDaftar, statusPkp, tanggalPengukuhanPkp, jenisWajibPajak, badanHukum, tahunGabung, jenisMitra, statusMitra, companyProfile, legalWp, id, callback)
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

    override fun Telaah(
        token: String,
        hasilTelaah: String,
        catatan: String,
        id:String,
        callback: DataSource.TelaahCallback
    ) {
        remoteDataSource.Telaah(token, hasilTelaah, catatan, id,callback)
    }

    override fun SetStatus(
        token: String,
        idStatus: Int,
        id: String,
        callback: DataSource.setStatusCallback
    ) {
        remoteDataSource.SetStatus(token, idStatus, id, callback)
    }

    override fun getNotification(token: String, callback: DataSource.NotificationCallback) {
        remoteDataSource.getNotification(token, callback)
    }

    override fun getNPWP(token: String, noNpwp: String, callback: DataSource.NPWPCallback) {
        remoteDataSource.getNPWP(token, noNpwp, callback)
    }

    override fun getTahun(token: String, callback: DataSource.tahunCallback) {
        remoteDataSource.getTahun(token, callback)
    }

    override fun getListMitra(token: String, callback: DataSource.listMitraCallback) {
        remoteDataSource.getListMitra(token, callback)
    }

    override fun getStatusMitra(token: String, callback: DataSource.statusMitraCallback) {
        remoteDataSource.getStatusMitra(token, callback)
    }

    override fun getTujuanPKS(token: String, callback: DataSource.tujuanPKSCallback) {
        remoteDataSource.getTujuanPKS(token, callback)
    }

    override fun getJenisMitra(token: String, callback: DataSource.jenisMitraCallback) {
        remoteDataSource.getJenisMitra(token, callback)
    }

    override fun getKategoriPKS(token: String, callback: DataSource.kategoriPKSCallback) {
        remoteDataSource.getKategoriPKS(token, callback)
    }

    override fun getKota(token: String, callback: DataSource.kotaCallback) {
        remoteDataSource.getKota(token, callback)
    }

    override fun getKelurahan(token: String, idKota: String, callback: DataSource.kelurahanCallback) {
       remoteDataSource.getKelurahan(token, idKota, callback)
    }

    override fun getDashboard(token: String, callback: DataSource.dashboardCallback) {
        remoteDataSource.getDashboard(token, callback)
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

    override fun getDetailMitra(
        token: String,
        id: String,
        callback: DataSource.detailMitraCallback
    ) {
        remoteDataSource.getDetailMitra(token, id, callback)
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