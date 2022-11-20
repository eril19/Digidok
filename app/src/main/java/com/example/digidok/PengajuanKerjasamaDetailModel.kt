package com.example.digidok

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PengajuanKerjasamaDetailModel(

    val kodeLokasi:String = "",
    val namaLokasi:String = "",
    val kodeBarang:String = "",
    val namaBarang:String = "",
    val alamat:String = "",
    val Luas:String = "",
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
    val tanggal: String = "",
    val keteranganSurat: String = "",
    val noreg: Long = 0,
    val keteranganAset: String = "",
    val luas: Long = 0,
    val satuan: String = "",
    val luasManfaat: Long = 0,

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
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(kodeLokasi)
        parcel.writeString(namaLokasi)
        parcel.writeString(kodeBarang)
        parcel.writeString(namaBarang)
        parcel.writeString(alamat)
        parcel.writeString(Luas)
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
        parcel.writeString(tanggal)
        parcel.writeString(keteranganSurat)
        parcel.writeLong(noreg)
        parcel.writeString(keteranganAset)
        parcel.writeLong(luas)
        parcel.writeString(satuan)
        parcel.writeLong(luasManfaat)
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