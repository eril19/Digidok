package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

class DaftarKjppModel(
    val no_kjpp : String,
    val nama_kjpp : String,
    val telp_kjpp : String,
    val no : String,
    val no_perizinan: String,
    val tgl : String,
    val tgl_perizinan : String,
    val klasifikasi : String,
    val klasifikasi_perizinan : String,
    val alamat:String,
): Parcelable  {

    constructor(parcel: Parcel) :this(

        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ){

    }
        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(nama_kjpp)
            parcel.writeString(tgl)
            parcel.writeString(tgl_perizinan)
            parcel.writeString(klasifikasi)
            parcel.writeString(klasifikasi_perizinan)
            parcel.writeString(alamat)
            parcel.writeString(no_perizinan)
            parcel.writeString(no_kjpp)
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
