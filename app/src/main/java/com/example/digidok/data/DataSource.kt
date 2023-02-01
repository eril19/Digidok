package com.example.digidok.data

import com.example.digidok.data.model.*
import retrofit2.http.Field
import retrofit2.http.Header

interface DataSource : BaseDataSource {

    fun login(
        user: String,
        password: String,
        deviceId: String,
        fid: String,
        callback: LoginDataCallback
    )

    fun updateProfile(
        token: String,
        nama: String,
        nip: String,
        telepon: String,
        email: String,
        keterangan: String,
        password: String,
        callback: updateProfileCallback
    )


    fun InsertPengajuan(
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
        callback: InsertPengajuanCallback
    )

    fun UpdatePengajuan(
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
        id:String,
        dokumen: String,
        callback: updatePengajuanCallback
    )

    fun InsertMitra(
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
        callback: InsertMitraCallback
    )

    fun updateMitra(
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
        id:String,
        callback: updateMitraCallback
    )

    fun getBerita(start: String, limit: String, callback: BeritaDataCallback)

    fun getProfile(token: String, callback: ProfileCallback)

    fun getDaftarMitra(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        statusFilter: Int,
        callback: daftarMitraCallback
    )

    fun getSetAktifNonAktif(
        token: String,
        kodeMitra: String,
        isAktif: Int,
        callback: setAktifNonAktifCallback
    )

    fun Telaah(
        token: String,
        hasilTelaah: String,
        catatan: String,
        id:String,
        callback: TelaahCallback
    )

    fun SetStatus(
        token: String,
        idStatus: Int,
        id: String,
        callback: setStatusCallback
    )

    fun getNotification(token: String, callback: NotificationCallback)
    fun getNPWP(token: String, noNpwp: String, callback: NPWPCallback)
    fun getTahun(token: String, callback: tahunCallback)
    fun getListMitra(token: String, callback: listMitraCallback)
    fun getStatusMitra(token: String, callback: statusMitraCallback)
    fun getTujuanPKS(token: String, callback: tujuanPKSCallback)
    fun getJenisMitra(token: String, callback: jenisMitraCallback)
    fun getKategoriPKS(token: String, callback: kategoriPKSCallback)
    fun getKota(token: String, callback: kotaCallback)
    fun getKelurahan(token: String, idKota: String, callback: kelurahanCallback)
    fun getDashboard(token: String, callback: dashboardCallback)

    fun getKJPP(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        callback: KJPPCallback
    )

    fun getDaftarPengajuanKerjasama(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        callback: daftarPengajuanCallback
    )

    fun getDaftarPengajuanKerjasamaDetail(
        token: String,
        id: String,
        callback: daftarPengajuanDetailCallback
    )

    fun getDetailMitra(
        token: String,
        id: String,
        callback: detailMitraCallback
    )

    fun getLaporanAsetDetail(
        token: String,
        id: String,
        callback: LaporanAsetDetailCallback
    )

    fun getCekDokumen(
        token: String,
        id: String,
        callback: CekDokumenCallback
    )

    fun getLaporanAsetDikerjasamakan(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        tahunFilter: String,
        kelurahanFilter: String,
        callback: laporanAsetDikerjasamakanCallback
    )

    fun getLaporanKerjasama(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        tahunFilter: String,
        kelurahanFilter: String,
        callback: laporanKerjasamaCallback
    )

    fun getRepositoriDokumen(
        token: String,
        start: Int,
        row: Int,
        order: String,
        sortColumn: String,
        search: String,
        statusFilter: String,
        tahunFilter: String,
        kelurahanFilter: String,
        callback: repositoriDokumenCallback
    )


    interface LoginDataCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface InsertMitraCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface BeritaDataCallback {
        fun onSuccess(data: BaseApiModel<BeritaModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface ProfileCallback {
        fun onSuccess(data: BaseApiModel<ProfileModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface daftarMitraCallback {
        fun onSuccess(data: BaseApiModel<daftarMitraModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface setAktifNonAktifCallback {
        fun onSuccess(data: BaseApiModel<setAktifNonAktifModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface NPWPCallback {
        fun onSuccess(data: BaseApiModel<NPWPModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface KJPPCallback {
        fun onSuccess(data: BaseApiModel<daftarKJPPModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface daftarPengajuanCallback {
        fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface daftarPengajuanDetailCallback {
        fun onSuccess(data: BaseApiModel<daftarPengajuanKerjasamaDetailModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface laporanAsetDikerjasamakanCallback {
        fun onSuccess(data: BaseApiModel<laporanAsetDikerjasamakanModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface laporanKerjasamaCallback {
        fun onSuccess(data: BaseApiModel<laporanKerjasamaModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface repositoriDokumenCallback {
        fun onSuccess(data: BaseApiModel<repositoriDokumenModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface kelurahanCallback {
        fun onSuccess(data: BaseApiModel<kelurahanModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface kotaCallback {
        fun onSuccess(data: BaseApiModel<kotaModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface tahunCallback {
        fun onSuccess(data: BaseApiModel<tahunModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface statusMitraCallback {
        fun onSuccess(data: BaseApiModel<statusMitramodel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface jenisMitraCallback {
        fun onSuccess(data: BaseApiModel<jenisMitramodel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface tujuanPKSCallback {
        fun onSuccess(data: BaseApiModel<tujuanPKSmodel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface kategoriPKSCallback {
        fun onSuccess(data: BaseApiModel<kategoriPKSmodel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface dashboardCallback {
        fun onSuccess(data: BaseApiModel<dashboardModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface detailMitraCallback {
        fun onSuccess(data: BaseApiModel<detailMitramodel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface updateProfileCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface InsertPengajuanCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface listMitraCallback {
        fun onSuccess(data: BaseApiModel<listMitra?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface updateMitraCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface TelaahCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface setStatusCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface updatePengajuanCallback {
        fun onSuccess(data: BaseApiModel<UserModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface NotificationCallback {
        fun onSuccess(data: BaseApiModel<notificationModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface LaporanAsetDetailCallback {
        fun onSuccess(data: BaseApiModel<laporanAsetDetailModel?>)

        fun onError(message: String)

        fun onFinish()
    }

    interface CekDokumenCallback {
        fun onSuccess(data: BaseApiModel<cekDokumenModel?>)

        fun onError(message: String)

        fun onFinish()
    }
}