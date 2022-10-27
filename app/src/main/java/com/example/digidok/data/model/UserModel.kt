package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

class UserModel(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("namaRole")
    val namaRole: String = "",
    @SerializedName("namaUser")
    val namaUser: String = "",
    @SerializedName("roles")
    val roles: List<Roles>

) {
    class Roles(
        @SerializedName("read")
        val read: Int,

        )
}