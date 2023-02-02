package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class laporanKerjasamaModel(
    @SerializedName("dataDokumen")
    val dataDokumen: List<listLaporanPengajuan>,
) {
    data class listLaporanPengajuan(
        @SerializedName("no")
        val no: Int,
        @SerializedName("idPks")
        val idPks: String = "",
        @SerializedName("kategoriPks")
        val kategoriPks: String = "",
        @SerializedName("noPks")
        val noPks: String = "",
        @SerializedName("nilaiPks")
        val nilaiPks: String,
        @SerializedName("objekPks")
        val objekPks: String = "",
        @SerializedName("namaMitra")
        val namaMitra: String = "",
        @SerializedName("idMitra")
        val idMitra: String,
        @SerializedName("alamatMitra")
        val alamatMitra: String = "",
        @SerializedName("perihalPks")
        val perihalPks: String = "",
        @SerializedName("periodeAwal")
        val periodeAwal: String = "",
        @SerializedName("periodeAkhir")
        val periodeAkhir: String = "",
        @SerializedName("status")
        val status: Int,
        @SerializedName("statusLabel")
        val statusLabel: String = "",
    )
}