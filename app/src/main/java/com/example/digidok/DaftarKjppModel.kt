package com.example.digidok

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DaftarKjppModel(
    val no_kjpp : String,
    val nama_kjpp : String,
    val telp_kjpp : String,
    val no_perizinan: String,
    val tgl_perizinan : String,
    val klasifikasi_perizinan : String,
): Parcelable
