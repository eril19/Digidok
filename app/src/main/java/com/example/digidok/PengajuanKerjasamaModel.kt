package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class PengajuanKerjasamaModel(
    val header_color : String,
    val id_mitra : String,
    val nama_mitra: String,
    val jenis_mitra : String,
////    val status : String,
//    val status_mitra: String,
////    val npwp : String,
//    val npwp_mitra : String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
//        parcel.readString().toString(),
////        parcel.readString().toString(),
//        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(id_mitra)
        parcel.writeString(nama_mitra)
        parcel.writeString(jenis_mitra)
////        parcel.writeString(status)
//        parcel.writeString(status_mitra)
////        parcel.writeString(npwp)
//        parcel.writeString(npwp_mitra)
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
