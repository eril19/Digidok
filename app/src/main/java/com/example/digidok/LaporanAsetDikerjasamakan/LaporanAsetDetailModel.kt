package com.example.digidok.LaporanAsetDikerjasamakan

import android.os.Parcel
import android.os.Parcelable


data class LaporanAsetDetailModel(
    val num_laporan: String = "",
    val nama_lokasi: String = "",
    val nama_bmd: String = "",
    val kode_lokasi: String = "",
    val kode_barang: String= "",
    val luas_bmd: String = "",
    val keterangan_bmd: String = "",
    val satuan:String = "",
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num_laporan)
        parcel.writeString(nama_lokasi)
        parcel.writeString(nama_bmd)
        parcel.writeString(kode_lokasi)
        parcel.writeString(kode_barang)
        parcel.writeString(luas_bmd)
        parcel.writeString(keterangan_bmd)
        parcel.writeString(satuan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LaporanAsetDetailModel> {
        override fun createFromParcel(parcel: Parcel): LaporanAsetDetailModel {
            return LaporanAsetDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<LaporanAsetDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}

