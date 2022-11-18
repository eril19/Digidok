package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


data class PengajuanKerjasamaModel(
    val header_color : String,
    val no_pks : String,
    val nama_mitra: String,
//    val jenis_mitra : String,
    val periodeAwal:Date,
    val periodeAkhir:Date,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        TODO("periodeAwal"),
        TODO("periodeAkhir")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(no_pks)
        parcel.writeString(nama_mitra)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PengajuanKerjasamaModel> {
        override fun createFromParcel(parcel: Parcel): PengajuanKerjasamaModel {
            return PengajuanKerjasamaModel(parcel)
        }

        override fun newArray(size: Int): Array<PengajuanKerjasamaModel?> {
            return arrayOfNulls(size)
        }
    }
}
