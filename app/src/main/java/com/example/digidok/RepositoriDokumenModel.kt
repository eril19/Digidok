package com.example.digidok

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoriDokumenModel(
    val header_color : String,
    val jenis_kerjasama : String,
    val no_surat: String,
    val nama_mitra: String,
): Parcelable
