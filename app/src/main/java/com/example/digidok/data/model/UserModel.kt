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
        @SerializedName("update")
        val update: Int,
        @SerializedName("delete")
        val delete: Int,
        @SerializedName("print")
        val print: Int,
        @SerializedName("special")
        val special: Int,
        @SerializedName("admin")
        val admin: Int,
        @SerializedName("namaMenu")
        val namaMenu: String = "",
        )
}