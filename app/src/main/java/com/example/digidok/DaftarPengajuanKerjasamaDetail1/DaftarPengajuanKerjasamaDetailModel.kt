package com.example.digidok.DaftarPengajuanKerjasamaDetail1

import android.os.Parcel
import android.os.Parcelable

data class DaftarPengajuanKerjasamaDetailModel(
    val kodeLokasi:String = "",
    val namaLokasi:String = "",
    val kodeBarang:String = "",
    val namaBarang:String = "",
    val dokumenLink : String = "",
    val lampiranLink : String = "",
    val alamat:String = "",
    val luas:String = "",
    val tglMulai:String = "",
    val tglAkhir :String = "",
    val perihal : String = "",
    val noPengajuan : String = "",
    val skemaPemanfaatan: String = "",
    val tujuan : String = "",
    val noSurat : String = "",
    val tglSurat :String = "",
    val objek : String = "",
    val kodeDokumen: String = "",
    val jenisDokumen: String = "",
    val amount: Long = 0,
    val tanggalDokumen: String = "",
    val keteranganSurat: String = "",
    val noreg: String = "",
    val keteranganAset: String = "",
    val satuan: String = "",
    val luasManfaat: String = "",

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
        parcel.readLong(),
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
        parcel.writeString(dokumenLink)
        parcel.writeString(luas)
        parcel.writeString(tglMulai)
        parcel.writeString(tglAkhir)
        parcel.writeString(perihal)
        parcel.writeString(noPengajuan)
        parcel.writeString(skemaPemanfaatan)
        parcel.writeString(tujuan)
        parcel.writeString(noSurat)
        parcel.writeString(tglSurat)
        parcel.writeString(objek)
        parcel.writeString(kodeDokumen)
        parcel.writeString(jenisDokumen)
        parcel.writeLong(amount)
        parcel.writeString(tanggalDokumen)
        parcel.writeString(keteranganSurat)
        parcel.writeString(noreg)
        parcel.writeString(keteranganAset)
        parcel.writeString(satuan)
        parcel.writeString(luasManfaat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DaftarPengajuanKerjasamaDetailModel> {
        override fun createFromParcel(parcel: Parcel): DaftarPengajuanKerjasamaDetailModel {
            return DaftarPengajuanKerjasamaDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<DaftarPengajuanKerjasamaDetailModel?> {
            return arrayOfNulls(size)
        }
    }

}