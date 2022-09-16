package com.example.digidok

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LaporanAsetDetailModel (
    val num_laporan: String,
    val nama_lokasi : String,
    val nama_bmd : String,
    val kode_lokasi: String,
    val kode_barang : String,
    val luas_bmd : String,
    val keterangan_bmd: String,
): Parcelable

