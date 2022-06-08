package com.example.usspeak.api

import android.content.Context
import android.content.SharedPreferences

class TokenPref(context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences(EMAIL, Context.MODE_PRIVATE)

    fun setToken(token: String) {
        val prefEditor: SharedPreferences.Editor = preference.edit()
        prefEditor.putString(TOKEN, token)
        prefEditor.apply()
    }

    fun getToken(): String {
        return preference.getString(TOKEN, "").toString()
    }

    fun removeToken() {
        val prefEditor: SharedPreferences.Editor = preference.edit()
        prefEditor.clear()
        prefEditor.apply()
    }

    companion object {
        const val EMAIL = "email"
        const val TOKEN = "token"
    }
}