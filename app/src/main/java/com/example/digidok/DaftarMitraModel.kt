package com.example.digidok

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarMitraModel(
    val header_color : String,
    val id_mitra : String,
    val nama_mitra: String,
    val jenis_mitra : String,
    val status : String,
    val status_mitra: String,
    val npwp : String,
    val npwp_mitra : String,
    val status_aktif: String
): Parcelable