package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class cekDokumenModel(
    @SerializedName("dataLampiran")
    val dataLampiran: List<listLampiran>,
) {
    data class listLampiran(
        @SerializedName("label")
        val label: String = "",
        @SerializedName("file")
        val file: String = "",
    )
}