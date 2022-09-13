package com.example.digidok

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaporanAsetModel(
    val header_color : String,
    val id_pks : String,
    val nama_mitra: String,
    val nilai_pks : String,
    val jenis_kerjasama : String
): Parcelable
