package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class notificationModel (
    @SerializedName("dataDokumen")
    val dataDokumen: List<listNotif>,
){
    data class listNotif(
        @SerializedName("idPks")
        val idPks: String = "",
        @SerializedName("namaMitra")
        val namaMitra: String,
        @SerializedName("status")
        val status: String,
    )

}