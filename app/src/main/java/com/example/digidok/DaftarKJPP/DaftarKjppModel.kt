package com.example.digidok.DaftarKJPP

import android.os.Parcel
import android.os.Parcelable

class DaftarKjppModel(
    val no_kjpp: String = "",
    val nama_kjpp: String = "",
    val telp_kjpp: String = "",
    val no_perizinan: String = "",
    val tgl_perizinan: String = "",
    val klasifikasi_perizinan: String = "",
    val alamat: String = "",
) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(no_kjpp)
        parcel.writeString(nama_kjpp)
        parcel.writeString(telp_kjpp)
        parcel.writeString(no_perizinan)
        parcel.writeString(tgl_perizinan)
        parcel.writeString(klasifikasi_perizinan)
        parcel.writeString(alamat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DaftarKjppModel> {
        override fun createFromParcel(parcel: Parcel): DaftarKjppModel {
            return DaftarKjppModel(parcel)
        }

        override fun newArray(size: Int): Array<DaftarKjppModel?> {
            return arrayOfNulls(size)
        }
    }
}