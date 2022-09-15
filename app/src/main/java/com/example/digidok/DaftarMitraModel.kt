package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class DaftarMitraModel(
    val header_color : String,
    val id_mitra : String,
    val nama_mitra: String,
    val jenis_mitra : String,
    val status : String,
    val status_mitra: String,
    val npwp : String,
    val npwp_mitra : String
//
//    val alamat_mitra: String,
//    val kelurahan_mitra: String,
//    val kecamatan_mitra: String,
//    val kota_mitra: String,
//    val provinsi_mitra: String,
//    val klasifikasi_mitra: String,
//    val kpp_mitra: String,
//    val kanwil_mitra: String,
//    val telp_mitra: String,
//    val fax_mitra: String,
//    val email_mitra: String,
//    val ttl_mitra: String,
//    val tgl_daftar_mitra: String,
//    val status_pkp_mitra: String,
//    val tgl_pkp_mitra: String,
//    val jeniw_pajak_mitra: String,
//    val badan_hukum_mitra: String

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
//
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header_color)
        parcel.writeString(id_mitra)
        parcel.writeString(nama_mitra)
        parcel.writeString(jenis_mitra)
        parcel.writeString(status)
        parcel.writeString(status_mitra)
        parcel.writeString(npwp)
        parcel.writeString(npwp_mitra)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DaftarMitraModel> {
        override fun createFromParcel(parcel: Parcel): DaftarMitraModel {
            return DaftarMitraModel(parcel)
        }

        override fun newArray(size: Int): Array<DaftarMitraModel?> {
            return arrayOfNulls(size)
        }
    }

}
