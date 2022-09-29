package com.example.digidok

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaporanPengajuanModel(
    val header_color : String,
    val id_mitra : String,
    val nama_mitra: String,
    val no_mitra: String,
    val id_pks : String,
    val jenis_kerjasama: String,
    val no_surat: String,
    val jenis_bmd : String,
    val nilai_pks: String,
    val detail_pks: String,
    val periode: String,
    val jangka_periode: String
): Parcelable

