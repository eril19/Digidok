package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class statusMitramodel(
    @SerializedName("dataStatusMitra")
    val dataStatusMitra: List<listStatusMitra>,
) {
    data class listStatusMitra(
        @SerializedName("value")
        val value: String,
        @SerializedName("label")
        val label: String,
    )

}