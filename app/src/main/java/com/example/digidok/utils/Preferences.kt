package com.example.digidok.utils

import android.content.Context
import android.preference.PreferenceManager

object Preferences {
    private val IS_LOGIN = "IS_LOGIN"

    fun isLogin(context: Context): Boolean {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        return preferenceManager.getBoolean(IS_LOGIN, false)
    }

    fun saveLogin(context: Context, status: Boolean) {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context).edit()
        preferenceManager.putBoolean(IS_LOGIN, status).apply()
    }
}