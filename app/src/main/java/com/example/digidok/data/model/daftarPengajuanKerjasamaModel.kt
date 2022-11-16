package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class daftarPengajuanKerjasamaModel(
    @SerializedName("dataDokumen")
    val dataDokumen: List<listDaftarPengajuan>,
) {
    data class listDaftarPengajuan(
        @SerializedName("no")
        val no: Int,
        @SerializedName("idPks")
        val idPks: String,
        @SerializedName("nama")
        val nama: String = "",
        @SerializedName("email")
        val email: String,
        @SerializedName("telp")
        val telp: String = "",
        @SerializedName("status")
        val status: Int,
        @SerializedName("periodeAwal")
        val periodeAwal: String = "",
        @SerializedName("periodeAkhir")
        val periodeAkhir: String = "",
    )
}