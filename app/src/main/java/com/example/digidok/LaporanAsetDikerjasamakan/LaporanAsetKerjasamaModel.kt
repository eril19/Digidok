package com.example.digidok.LaporanAsetDikerjasamakan

import android.os.Parcel
import android.os.Parcelable

class LaporanAsetKerjasamaModel(
    val header_color : String = "",
    val id_pks : String= "",
    val nama_mitra: String= "",
    val nilai_pks : String= "",
    val jenis_kerjasama : String= "",

): Parcelable  {

    constructor(parcel: Parcel) :this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ){

    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(id_pks)
        parcel.writeString(nama_mitra)
        parcel.writeString(nilai_pks)
        parcel.writeString(jenis_kerjasama)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LaporanAsetKerjasamaModel> {
        override fun createFromParcel(parcel: Parcel): LaporanAsetKerjasamaModel {
            return LaporanAsetKerjasamaModel(parcel)
        }

        override fun newArray(size: Int): Array<LaporanAsetKerjasamaModel?> {
            return arrayOfNulls(size)
        }
    }


}
