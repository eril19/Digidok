package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class NPWPModel(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("kelurahan")
    val kelurahan: String,
    @SerializedName("kecamatan")
    val kecamatan: String,
    @SerializedName("kabKota")
    val kabKota: String,
    @SerializedName("provinsi")
    val provinsi: String,
    @SerializedName("klasifikasiKlu")
    val klasifikasiKlu: String,
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
) {
}