package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class PengajuanKerjasamaModel(
    val header_color : String,
    val id_pks : String,
    val nama_mitra: String,
    val jenis_mitra : String,
    val noPengajuan : String,
    val skemaPemanfaatan: String,
    val tujuan : String,
    val noSurat : String,
    val tglSurat :String,
    val objek : String,
    val nilai:String,
    val tglMulai:String,
    val tglAkhir :String,
    val perihal : String,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
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
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(id_pks)
        parcel.writeString(nama_mitra)
        parcel.writeString(jenis_mitra)
        parcel.writeString(noPengajuan)
        parcel.writeString(skemaPemanfaatan)
        parcel.writeString(tujuan)
        parcel.writeString(noSurat)
        parcel.writeString(tglSurat)
        parcel.writeString(objek)
        parcel.writeString(nilai)
        parcel.writeString(tglMulai)
        parcel.writeString(tglAkhir)
        parcel.writeString(perihal)
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
