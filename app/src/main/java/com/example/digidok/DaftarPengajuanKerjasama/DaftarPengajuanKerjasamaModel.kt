package com.example.digidok.DaftarPengajuanKerjasama

import android.os.Parcel
import android.os.Parcelable


data class DaftarPengajuanKerjasamaModel(
    val header_color : String,
    val no_pks : String,
    val nama_mitra: String,
//    val jenis_mitra : String,
    val periodeAwal:String,
    val periodeAkhir:String,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(no_pks)
        parcel.writeString(nama_mitra)
        parcel.writeString(periodeAwal)
        parcel.writeString(periodeAkhir)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DaftarPengajuanKerjasamaModel> {
        override fun createFromParcel(parcel: Parcel): DaftarPengajuanKerjasamaModel {
            return DaftarPengajuanKerjasamaModel(parcel)
        }

        override fun newArray(size: Int): Array<DaftarPengajuanKerjasamaModel?> {
            return arrayOfNulls(size)
        }
    }
}