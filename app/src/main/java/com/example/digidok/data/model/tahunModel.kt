package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class tahunModel(
    @SerializedName("dataTahun")
    val dataTahun: List<listTahun>,
) {
    data class listTahun(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )
}