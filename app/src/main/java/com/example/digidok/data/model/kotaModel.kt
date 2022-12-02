package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class kotaModel(
    @SerializedName("dataKota")
    val dataKota: List<listKota>,
) {
    data class listKota(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )
}