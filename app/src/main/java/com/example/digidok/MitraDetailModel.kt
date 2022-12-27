package com.example.digidok

import android.os.Parcel
import android.os.Parcelable

class MitraDetailModel(
    var npwp: String = "",
    var nama: String = "",
    var alamat: String= "",
    var kelurahan: String= "",
    var kecamatan: String= "",
    var kotaKabupaten: String= "",
    var provinsi: String= "",
    var klasifikasi: String= "",
    var namaKpp: String= "",
    var kanwil: String= "",
    var nomorTelepon: String= "",
    var nomorFax: String= "",
    var email: String= "",
    var ttl: String= "",
    var tanggalDaftar: String= "",
    var statusPkp: String= "",
    var tanggalPengukuhanPkp: String= "",
    var jenisWajibPajak: String= "",
    var badanHukum: String= "",
    var tahunGabung: String= "",
    var jenisMitra: String= "",
    var statusMitra: String= "",
    var legalWp: Long = 0,
    var companyProfile: String= "",
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
        parcel.readLong(),
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
