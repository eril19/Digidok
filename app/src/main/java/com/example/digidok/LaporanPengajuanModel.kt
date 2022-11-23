package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class LaporanPengajuanModel(
    val header_color : String = "",
    val id_mitra : String = "",
    val nama_mitra: String = "",
    val no_mitra: String = "",
    val perihal: String = "",
    val alamatMitra: String = "",
    val id_pks : String = "",
    val jenis_kerjasama: String = "",
    val no_surat: String = "",
    val jenis_bmd : String = "",
    val nilai_pks: String = "",
    val detail_pks: String = "",
    val periodeAwal: String = "",
    val periodeAkhir: String = ""
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
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(id_mitra)
        parcel.writeString(nama_mitra)
        parcel.writeString(no_mitra)
        parcel.writeString(id_pks)
        parcel.writeString(jenis_kerjasama)
        parcel.writeString(no_surat)
        parcel.writeString(jenis_bmd)
        parcel.writeString(nilai_pks)
        parcel.writeString(detail_pks)
        parcel.writeString(periodeAwal)
        parcel.writeString(periodeAkhir)
        parcel.writeString(alamatMitra)
        parcel.writeString(perihal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LaporanPengajuanModel> {
        override fun createFromParcel(parcel: Parcel): LaporanPengajuanModel {
            return LaporanPengajuanModel(parcel)
        }

        override fun newArray(size: Int): Array<LaporanPengajuanModel?> {
            return arrayOfNulls(size)
        }
    }
}

