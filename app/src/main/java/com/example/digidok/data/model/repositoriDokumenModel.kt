package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class repositoriDokumenModel(
    @SerializedName("dataDokumen")
    val dataMitra: List<listRepositoriDokumen>,
) {
    data class listRepositoriDokumen(
        @SerializedName("no")
        val no: Int,
        @SerializedName("idPks")
        val idPks: String = "",
        @SerializedName("kategoriPks")
        val kategoriPks: String = "",
        @SerializedName("nilaiPks")
        val nilaiPks: String = "",
        @SerializedName("dataLampiran")
        val dataLampiran: List<listLampiran>,
        @SerializedName("namaMitra")
        val namaMitra: String = "",
        @SerializedName("status")
        val status: Int,
    )

    data class listLampiran(
        @SerializedName("label")
        val label: String = "",
        @SerializedName("file")
        val file: String = "",
    )
}