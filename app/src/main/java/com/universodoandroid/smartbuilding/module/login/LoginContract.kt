package com.universodoandroid.smartbuilding.module.login

interface LoginContract {
    interface View {
        fun saveToken(token: String)
        fun onError(error: String)
    }
}