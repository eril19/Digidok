package com.example.digidok

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationModel(
    val NotificationTitle : String,
    val NotificationDetail: String,
    val NotificationDateTime: String
): Parcelable