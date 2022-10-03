package com.example.digidok

import android.os.Parcel
import android.os.Parcelable

data class PengajuanKerjasamaDetailModel(

    val kodeLokasi:String,
    val namaLokasi:String,
    val kodeBarang:String,
    val namaBarang:String,
    val alamat:String,
    val Luas:String,
    val LuasManfaat:String

):Parcelable {
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
        parcel.writeString(kodeLokasi)
        parcel.writeString(namaLokasi)
        parcel.writeString(kodeBarang)
        parcel.writeString(namaBarang)
        parcel.writeString(alamat)
        parcel.writeString(Luas)
        parcel.writeString(LuasManfaat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PengajuanKerjasamaDetailModel> {
        override fun createFromParcel(parcel: Parcel): PengajuanKerjasamaDetailModel {
            return PengajuanKerjasamaDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<PengajuanKerjasamaDetailModel?> {
            return arrayOfNulls(size)
        }
    }

}