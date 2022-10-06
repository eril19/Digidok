package com.example.digidok.data.model

import com.google.gson.annotations.SerializedName

data class BaseApiModel<T>(
    @SerializedName("status") val status: Int,
    @SerializedName("success") val success: Boolean,
    @SerializedName("msg") val message: String,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message1: String,
    @SerializedName("data") val data: T? = null,
    @SerializedName("user") val user: T? = null,
    @SerializedName("rows") val rows: List<T>? = null)