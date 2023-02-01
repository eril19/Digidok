package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class daftarPengajuanKerjasamaDetailModel(
    @SerializedName("noPengajuan")
    val noPengajuan: String,
    @SerializedName("mitra")
    val mitra: String,
    @SerializedName("idMitra")
    val idMitra: String,
    @SerializedName("idSkemaPemanfaatan")
    val idSkemaPemanfaatan: String,
    @SerializedName("idTujuan")
    val idTujuan: String,
    @SerializedName("skemaPemanfaatan")
    val skemaPemanfaatan: String,
    @SerializedName("tujuan")
    val tujuan: String,
    @SerializedName("nomorSurat")
    val nomorSurat: String,
    @SerializedName("tanggalSurat")
    val tanggalSurat: String,
    @SerializedName("objek")
    val objek: String,
    @SerializedName("nilai")
    val nilai: String,
    @SerializedName("tanggalMulai")
    val tanggalMulai: String,
    @SerializedName("tanggalAkhir")
    val tanggalAkhir: String,
    @SerializedName("perihal")
    val perihal: String,
    @SerializedName("dokumen")
    val dokumen: String,
    @SerializedName("dataLampiran")
    val dataLampiran: List<listDataLampiran>,
    @SerializedName("dataAsetDikerjasamakan")
    val dataAsetDikerjasamakan: List<listDataAsetDikerjasamakan>,
) {
    data class listDataLampiran(
        @SerializedName("kodeDokumen")
        val kodeDokumen: String,
        @SerializedName("jenisDokumen")
        val jenisDokumen: String,
        @SerializedName("amount")
        val amount: String,
        @SerializedName("noSurat")
        val noSurat: String,
        @SerializedName("tanggal")
        val tanggal: String = "",
        @SerializedName("keterangan")
        val keterangan: String,
        @SerializedName("file")
        val file: String,
    )
    data class listDataAsetDikerjasamakan(
        @SerializedName("kolok")
        val kolok: String,
        @SerializedName("nalok")
        val nalok: String,
        @SerializedName("nabar")
        val nabar: String,
        @SerializedName("kobar")
        val kobar: String,
        @SerializedName("noreg")
        val noreg: String,
        @SerializedName("keterangan")
        val keterangan: String,
        @SerializedName("luas")
        val luas: String,
        @SerializedName("satuan")
        val satuan: String,
        @SerializedName("luasManfaat")
        val luasManfaat: String,
        )
}