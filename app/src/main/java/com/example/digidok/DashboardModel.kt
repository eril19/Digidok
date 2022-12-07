package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
//import kotlinx.parcelize.Parcelize

//@Parcelize
data class DashboardModel(
    var nama_mitra: String = "",
    var jenis_mitra : String = "",
    var jumlah_kerjasama : String = "",
    var total_nilai :String = "",
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama_mitra)
        parcel.writeString(jenis_mitra)
        parcel.writeString(jumlah_kerjasama)
        parcel.writeString(total_nilai)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DashboardModel> {
        override fun createFromParcel(parcel: Parcel): DashboardModel {
            return DashboardModel(parcel)
        }

        override fun newArray(size: Int): Array<DashboardModel?> {
            return arrayOfNulls(size)
        }
    }
}