package com.example.digidok

import android.os.Parcel
import android.os.Parcelable


class RepositoriDokumenModel(
    val header_color: String,
    val jenis_kerjasama: String,
    val no_surat: String,
    val nama_mitra: String,

    ): Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(jenis_kerjasama)
        parcel.writeString(no_surat)
        parcel.writeString(nama_mitra)
    }

    companion object CREATOR : Parcelable.Creator<RepositoriDokumenModel> {
        override fun createFromParcel(parcel: Parcel): RepositoriDokumenModel {
            return RepositoriDokumenModel(parcel)
        }

        override fun newArray(size: Int): Array<RepositoriDokumenModel?> {
            return arrayOfNulls(size)
        }
    }
}
