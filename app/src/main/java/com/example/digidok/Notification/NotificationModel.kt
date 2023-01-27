package com.example.digidok.Notification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationModel(
    val NotificationTitle : String,
    val NotificationDetail: String,
    val status: String
): Parcelable