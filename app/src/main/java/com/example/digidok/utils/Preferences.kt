package com.example.digidok.utils

import android.content.Context
import android.preference.PreferenceManager

object Preferences {
    private val IS_LOGIN = "IS_LOGIN"
    private val IS_USER = "IS_USER"

    fun isLogin(context: Context): Boolean {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        return preferenceManager.getBoolean(IS_LOGIN, false)
    }

    fun saveLogin(context: Context, status: Boolean) {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context).edit()
        preferenceManager.putBoolean(IS_LOGIN, status).apply()
    }

    fun isUser(context: Context): String {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        return preferenceManager.getString(IS_USER, "").safe()
    }

    fun saveUser(context: Context, user: String) {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context).edit()
        preferenceManager.putString(IS_USER, user).apply()
    }

    fun String?.safe(default: String = "-"): String {
        return if (this.isNullOrEmpty()) {
            default
        }
        else {
            this
        }
    }
}
