package com.example.digidok

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationModel(
    val imgIcon : Int,
    val NotificationTitle : String,
    val NotificationDetail: String
): Parcelable