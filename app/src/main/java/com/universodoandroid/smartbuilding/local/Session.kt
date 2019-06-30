package com.universodoandroid.smartbuilding.local

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class Session(val context: Context) {

    private val keyToken = "key_token"
    private val keyPreferencesReference = "session"

    private val sharedPreferencesSetup: SharedPreferences
    private val sharedPreferencesEditor: SharedPreferences.Editor

    init {
        sharedPreferencesSetup = getSharedPreferences()
        sharedPreferencesEditor = sharedPreferencesSetup.edit()
    }

    fun saveStateLoginSuccess(token: String) {
        sharedPreferencesEditor.putString(keyToken, token)
        sharedPreferencesEditor.apply()
    }

    fun deleteStateLogin() {
        sharedPreferencesEditor.putString(keyToken, null)
        sharedPreferencesEditor.clear()
        sharedPreferencesEditor.apply()
    }

    fun isLoggedIn(): Boolean? {
        return sharedPreferencesSetup.getString(keyToken, null)?.isNotEmpty()
    }

    fun getToken(): String? {
        return sharedPreferencesSetup.getString(keyToken, null)
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(keyPreferencesReference, MODE_PRIVATE)
    }


}