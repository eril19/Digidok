package com.example.digidok

import android.os.Parcel
import android.os.Parcelable

class MitraDetailModel(
    val npwp: String = "",
    val nama: String = "",
    val alamat: String= "",
    val kelurahan: String= "",
    val kecamatan: String= "",
    val kotaKabupaten: String= "",
    val provinsi: String= "",
    val klasifikasi: String= "",
    val namaKpp: String= "",
    val kanwil: String= "",
    val nomorTelepon: String= "",
    val nomorFax: String= "",
    val email: String= "",
    val ttl: String= "",
    val tanggalDaftar: String= "",
    val statusPkp: String= "",
    val tanggalPengukuhanPkp: String= "",
    val jenisWajibPajak: String= "",
    val badanHukum: String= "",
    val tahunGabung: String= "",
    val jenisMitra: String= "",
    val statusMitra: String= "",
    val companyProfile: String= "",
):Parcelable{
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
        parcel.writeString(npwp)
        parcel.writeString(nama)
        parcel.writeString(alamat)
        parcel.writeString(kelurahan)
        parcel.writeString(kecamatan)
        parcel.writeString(kotaKabupaten)
        parcel.writeString(provinsi)
        parcel.writeString(klasifikasi)
        parcel.writeString(namaKpp)
        parcel.writeString(kanwil)
        parcel.writeString(nomorTelepon)
        parcel.writeString(nomorFax)
        parcel.writeString(email)
        parcel.writeString(ttl)
        parcel.writeString(tanggalDaftar)
        parcel.writeString(statusPkp)
        parcel.writeString(tanggalPengukuhanPkp)
        parcel.writeString(jenisWajibPajak)
        parcel.writeString(badanHukum)
        parcel.writeString(tahunGabung)
        parcel.writeString(jenisMitra)
        parcel.writeString(statusMitra)
        parcel.writeString(companyProfile)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MitraDetailModel> {
        override fun createFromParcel(parcel: Parcel): MitraDetailModel {
            return MitraDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<MitraDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}
