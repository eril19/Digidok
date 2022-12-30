package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class listMitra(
    @SerializedName("dataMitra")
    val dataMitra: List<listMitra>,
) {
    data class listMitra(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )
}