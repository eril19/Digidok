package com.example.digidok

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CekDokumenModel (
    val header_color : String,
    val nama_dokumen : String
): Parcelable

