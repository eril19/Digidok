package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class detailMitramodel(
    @SerializedName("idMitra")
    val idMitra: String,
    @SerializedName("tahunGabung")
    val tahunGabung: Long,
    @SerializedName("jenisMitra")
    val jenisMitra: String,
    @SerializedName("statusMitra")
    val statusMitra: String,
    @SerializedName("npwp")
    val npwp: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("kelurahan")
    val kelurahan: String,
    @SerializedName("kecamatan")
    val kecamatan: String,
    @SerializedName("kotaKabupaten")
    val kotaKabupaten: String,
    @SerializedName("provinsi")
    val provinsi: String,
    @SerializedName("klasifikasi")
    val klasifikasi: String,
    @SerializedName("namaKpp")
    val namaKpp: String,
    @SerializedName("kanwil")
    val kanwil: String,
    @SerializedName("nomorTelepon")
    val nomorTelepon: String,
    @SerializedName("nomorFax")
    val nomorFax: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("ttl")
    val ttl: String,
    @SerializedName("tanggalDaftar")
    val tanggalDaftar: String,
    @SerializedName("statusPkp")
    val statusPkp: String,
    @SerializedName("tanggalPengukuhanPkp")
    val tanggalPengukuhanPkp: String,
    @SerializedName("jenisWajibPajak")
    val jenisWajibPajak: String,
    @SerializedName("badanHukum")
    val badanHukum: String,
    @SerializedName("legalWp")
    val legalWp: Long,
    @SerializedName("status")
    val status: Long,
) {
}