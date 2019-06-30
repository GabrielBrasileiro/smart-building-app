package com.universodoandroid.smartbuilding.module.login

interface LoginContract {
    interface Activity {
        fun saveToken(token: String)
        fun onError(error: String)
    }
}