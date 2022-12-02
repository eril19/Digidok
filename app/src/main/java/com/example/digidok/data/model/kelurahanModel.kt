package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class kelurahanModel(
    @SerializedName("dataKelurahan")
    val dataKelurahan: List<listKelurahan>,
) {
    data class listKelurahan(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )
}