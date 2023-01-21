package com.example.digidok.CekDokumen

import android.os.Parcel
import android.os.Parcelable


class CekDokumenModel (
    val header_color : String,
    val nama_dokumen : String,
    val file: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(nama_dokumen)
        parcel.writeString(file)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CekDokumenModel> {
        override fun createFromParcel(parcel: Parcel): CekDokumenModel {
            return CekDokumenModel(parcel)
        }

        override fun newArray(size: Int): Array<CekDokumenModel?> {
            return arrayOfNulls(size)
        }
    }
}

