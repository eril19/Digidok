package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class UserModel (
    @SerializedName("id")
    val id: String = "",
    @SerializedName("username")
    val username: String = "",
    @SerializedName("nama")
    val nama: String = "")