package com.universodoandroid.smartbuilding.module.login

import com.universodoandroid.smartbuilding.domain.User

interface LoginHandler {
    fun auth(user: User)
}