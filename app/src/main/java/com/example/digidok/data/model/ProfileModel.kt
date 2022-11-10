package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class ProfileModel (
    @SerializedName("userId")
    val userId: String = "",
    @SerializedName("nama")
    val nama: String = "",
    @SerializedName("nip")
    val nip: String = "",
    @SerializedName("noHp")
    val noHp: String,
    @SerializedName("email")
    val email : String,
    @SerializedName("description")
    val description : String,

    )